package DatabaseMgmt;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Announcement;
import CourseManagement.Model.Course;
import CourseManagement.Model.Lesson;
import CourseManagement.View.AnnouncementMgmtUI;
import CourseManagement.View.EditAnnouncementUI;
import CourseManagement.View.LessonMgmtUI;
import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.*;
import UserAuthentication.Controller.LoginController;
import UserAuthentication.Model.Instructor;
import UserAuthentication.Model.Student;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private Connection connection;
    private String pcUserName = System.getenv("USERNAME");

    public DatabaseConnection(){}

    public void openConnection()
    {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//" + pcUserName + "//OneDrive - The Pennsylvania State University//Database//LMSDB" + pcUserName + ".accdb");
        }
        catch (Exception ee)
        {
            System.out.println(ee);
        }
    }

    public boolean getUserLoginInfo(LoginController loginController){
        openConnection();
        String userName = loginController.getU1().getUserName().toLowerCase();
        try
        {
            String query = "SELECT * FROM UserTable WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            int column = rs.findColumn("password");

            if (rs.next())
            {
                if (rs.getString(column).equals(loginController.getU1().getPassword()))
                {
                    loginController.getU1().setUserIDNumber(rs.getInt("id"));
                    loginController.getU1().setFirstName(rs.getString("firstname"));
                    loginController.getU1().setLastName(rs.getString("lastname"));
                    loginController.getU1().setRoleID(rs.getString("roleID"));
                    return true;
                }
            }
        }
        catch (Exception ee)
        {
            System.out.println(ee);;
        }
        closeConnection();
        return false;
    }

    public Instructor getInstructorForCourse(int instructorID) {
        try
        {
            String query = "SELECT * FROM UserTable WHERE ID = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, instructorID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next())
            {
                int idNumber = rs.getInt("ID");
                String userName = rs.getString("username");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String password = rs.getString("password");
                String roleID = String.valueOf(rs.getInt("roleid"));
                return new Instructor(idNumber, userName, firstName, lastName, password, roleID);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Instructor> getAllInstructorsForSelection(){
        ArrayList<Instructor> instructorList = new ArrayList<>();
        openConnection();
        try
        {
            String query = "SELECT * FROM UserTable WHERE roleid= ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, 2);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int idNumber = rs.getInt("ID");
                String userName = rs.getString("username");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String password = rs.getString("password");
                String roleID = String.valueOf(rs.getInt("roleid"));
                instructorList.add(new Instructor(idNumber, userName, firstName, lastName, password, roleID));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
        return instructorList;
    }

    public void getInstructorCourseList(CourseMgmtController courseMgmtController) {
        openConnection();
        int userID = courseMgmtController.getHomepageController().getUser().getUserIDNumber();
        try
        {
            String query = "SELECT CourseTable.ID, CourseTable.courseid, CourseTable.coursename, CourseTable.maxenrolled, CourseTable.instructorid  "
                    + "FROM CourseTable "
                    + "WHERE CourseTable.instructorid = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int tableID = rs.getInt("ID");
                String id = rs.getString("courseid");
                String name = rs.getString("coursename");
                int enrolled = rs.getInt("maxenrolled");
                int instructorID = rs.getInt("instructorid");
                String enrolledConverted = String.valueOf(enrolled);
                Instructor instructor = getInstructorForCourse(instructorID);
                Course course = new Course(tableID, id, name, enrolledConverted, instructor);
                courseMgmtController.getCourseList().getCourses().add(course);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void getAdminCourseList(CourseMgmtController courseMgmtController) {
        openConnection();
        try
        {
            String query = "SELECT CourseTable.ID, CourseTable.courseid, CourseTable.coursename, CourseTable.maxenrolled, CourseTable.instructorid  "
                    + "FROM CourseTable ";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                int tableID = rs.getInt("ID");
                String id = rs.getString("courseid");
                String name = rs.getString("coursename");
                int enrolled = rs.getInt("maxenrolled");
                int instructorID = rs.getInt("instructorid");
                String enrolledConverted = String.valueOf(enrolled);
                Instructor instructor = getInstructorForCourse(instructorID);
                Course course = new Course(tableID, id, name, enrolledConverted, instructor);
                courseMgmtController.getCourseList().getCourses().add(course);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void getStudentCourseList(CourseMgmtController courseMgmtController)
    {
        openConnection();
        int userID = courseMgmtController.getHomepageController().getUser().getUserIDNumber();
        try
        {
            String query = "SELECT CourseTable.ID, CourseTable.courseid, CourseTable.coursename, CourseTable.maxenrolled, CourseTable.instructorid  "
                    + "FROM CourseTable "
                    + "JOIN StudentEnrolledTable ON CourseTable.ID = StudentEnrolledTable.courseid "
                    + "WHERE StudentEnrolledTable.userid = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int tableID = rs.getInt("ID");
                String id = rs.getString("courseid");
                String name = rs.getString("coursename");
                int enrolled = rs.getInt("maxenrolled");
                int instructorID = rs.getInt("instructorid");
                String enrolledConverted = String.valueOf(enrolled);
                Instructor instructor = getInstructorForCourse(instructorID);
                Course course = new Course(tableID, id, name, enrolledConverted, instructor);
                courseMgmtController.getCourseList().getCourses().add(course);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        closeConnection();
    }

    public void addCourseToDatabase(CourseMgmtController courseMgmtController) {
        openConnection();
        Course courseToAdd = courseMgmtController.getNewCourse();
        int maxEnrolled = Integer.parseInt(courseToAdd.getMaxEnrolled());
        int instructorID = courseToAdd.getInstructor().getUserIDNumber();

        try {
            String query = "INSERT INTO CourseTable (courseid, coursename, maxenrolled, instructorid) "
                    + "VALUES (?, ?, ?,?)";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, courseToAdd.getCourseID());
            pstmt.setString(2, courseToAdd.getCourseName());
            pstmt.setInt(3, maxEnrolled);
            pstmt.setInt(4, instructorID);

            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void editCourseInDatabase(Course course){
        openConnection();
        String courseID = course.getCourseID();
        String courseName = course.getCourseName();
        String maxEnrolled = course.getMaxEnrolled();
        int instructorID = course.getInstructor().getUserIDNumber();
        int tableID = course.getCourseTableID();
        try{
            String query = "UPDATE CourseTable "
                    + "SET CourseTable.courseid = ?, CourseTable.coursename = ?, CourseTable.maxenrolled = ?, CourseTable.instructorid = ? "
                    + "WHERE CourseTable.ID = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, courseID);
            pstmt.setString(2, courseName);
            pstmt.setString(3, maxEnrolled);
            pstmt.setInt(4, instructorID);
            pstmt.setInt(5, tableID);
            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void getCourseLessonList(CourseMgmtController courseMgmtController) {
        openConnection();
        try {
            int courseID = courseMgmtController.getSelectedCourse().getCourseTableID();

            String query = "SELECT LessonTable.ID, LessonTable.lessontitle, LessonTable.lessoncontent, LessonTable.assignedreading "
                    + "FROM LessonTable "
                    + "WHERE LessonTable.courseid = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, courseID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int lessonID = rs.getInt("ID");
                String lessonTitle = rs.getString("lessontitle");
                String lessonContent = rs.getString("lessoncontent");
                String assignedReading = rs.getString("assignedreading");

                Lesson lesson = new Lesson(lessonTitle, lessonContent, assignedReading);
                lesson.setLessonID(lessonID);
                lesson.setCourseTableID(courseID);
                courseMgmtController.getLessonList().getLessons().add(lesson);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void addLessonToDatabase(LessonMgmtUI lessonMgmtUI) {
        openConnection();
        Lesson lessonToAdd = lessonMgmtUI.getNewLesson();

        try {
            String query = "INSERT INTO LessonTable (courseid, lessontitle, lessoncontent, assignedreading) "
                    + "VALUES (?, ?, ?,?)";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, lessonToAdd.getCourseTableID());
            pstmt.setString(2, lessonToAdd.getPageTitle());
            pstmt.setString(3, lessonToAdd.getLessonContent());
            pstmt.setString(4, lessonToAdd.getAssignedReading());

            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void editLessonInDatabase(Lesson lesson){
        openConnection();
        int lessonID = lesson.getLessonID();
        String lessonTitle = lesson.getPageTitle();
        String lessonContent = lesson.getLessonContent();
        String lessonReading = lesson.getAssignedReading();
        try{
            String query = "UPDATE LessonTable "
                    + "SET LessonTable.lessontitle = ?, LessonTable.lessoncontent = ?, LessonTable.assignedreading = ? "
                    + "WHERE LessonTable.ID = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, lessonTitle);
            pstmt.setString(2, lessonContent);
            pstmt.setString(3, lessonReading);
            pstmt.setInt(4, lessonID);
            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void deleteLessonFromDatabase(Lesson lesson){
        openConnection();
        int lessonID = lesson.getLessonID();

        try{
            String query = "DELETE FROM LessonTable "
                    + "WHERE ID = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, lessonID);
            pstmt.executeUpdate();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void getCourseAnnouncementList(CourseMgmtController courseMgmtController) {
        openConnection();
        try {
            int courseID = courseMgmtController.getSelectedCourse().getCourseTableID();

            String query = "SELECT AnnouncementTable.ID, AnnouncementTable.announcementtitle, AnnouncementTable.announcementbody "
                    + "FROM AnnouncementTable "
                    + "WHERE AnnouncementTable.courseid = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, courseID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int announcementID = rs.getInt("ID");
                String announcementTitle = rs.getString("announcementtitle");
                String announcementBody = rs.getString("announcementbody");

                Announcement announcement = new Announcement(announcementTitle, announcementBody);
                announcement.setAnnouncementID(announcementID);
                announcement.setCourseTableID(courseID);
                courseMgmtController.getAnnouncementList().getAnnouncements().add(announcement);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void getStudentListForCourse(Course course)
    {
        openConnection();
        int courseID = course.getCourseTableID();
        try
        {
            String query = "SELECT UserTable.* "
                    + "FROM UserTable "
                    + "JOIN StudentEnrolledTable ON UserTable.ID = StudentEnrolledTable.userid "
                    + "WHERE StudentEnrolledTable.courseid = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, courseID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int studentID = rs.getInt("ID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fName = rs.getString("firstname");
                String lName = rs.getString("lastname");
                String roleID = String.valueOf(rs.getInt("roleid"));

                Student student = new Student(username, password);
                student.setUserIDNumber(studentID);
                student.setFirstName(fName);
                student.setLastName(lName);
                student.setRoleID(roleID);

                course.getStudentsEnrolled().add(student);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public AssignmentList getAllStudentAssignmentByCourse(CourseworkMgmtController courseworkMgmtController, ArrayList<Student> students) {
        openConnection();
        int courseTableID = courseworkMgmtController.getCurrentCourse().getCourseTableID();
        AssignmentList assignmentList = courseworkMgmtController.getAssignmentList();

        for (Student student : students)
        {
            int userID = student.getUserIDNumber();
            try
            {
                String query = "SELECT AssignmentTable.ID, AssignmentTable.AssignmentName, StudentAssignmentTable.completed, StudentAssignmentTable.grade  "
                        + "FROM AssignmentTable "
                        + "JOIN StudentAssignmentTable ON AssignmentTable.ID = StudentAssignmentTable.assignmentid "
                        + "WHERE AssignmentTable.CourseID = ? AND StudentAssignmentTable.userid = ? ";

                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, courseTableID);
                pstmt.setInt(2, userID);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next())
                {
                    int assignmentID = rs.getInt("ID");
                    String assignmentTitle = rs.getString("AssignmentName");
                    boolean completed = rs.getBoolean("completed");

                    Assignment assignment = new Assignment(assignmentTitle);
                    assignment.setAssignmentID(assignmentID);
                    assignment.setQuestionList(getQuestionsByAssignment(assignment));
                    assignment.setCompleted(completed);
                    assignment.setAssignedStudent(student);
                    assignmentList.getAssignments().add(assignment);
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        closeConnection();
        return assignmentList;
    }

    public AssignmentList getStudentAssignmentByCourse(CourseworkMgmtController courseworkMgmtController) {
        openConnection();
        int courseTableID = courseworkMgmtController.getCurrentCourse().getCourseTableID();
        int userID = courseworkMgmtController.getCurrentUser().getUserIDNumber();
        AssignmentList assignmentList = new AssignmentList();
        try
        {
            String query = "SELECT AssignmentTable.ID, AssignmentTable.AssignmentName, StudentAssignmentTable.completed, StudentAssignmentTable.grade  "
                    + "FROM AssignmentTable "
                    + "JOIN StudentAssignmentTable ON AssignmentTable.ID = StudentAssignmentTable.assignmentid "
                    + "WHERE AssignmentTable.CourseID = ? AND StudentAssignmentTable.userid = ? ";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, courseTableID);
            pstmt.setInt(2, userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int assignmentID = rs.getInt("ID");
                String assignmentTitle = rs.getString("AssignmentName");
                boolean completed = rs.getBoolean("completed");

                Assignment assignment = new Assignment(assignmentTitle);
                assignment.setAssignmentID(assignmentID);
                assignment.setQuestionList(getQuestionsByAssignment(assignment));
                assignment.setCompleted(completed);
                assignmentList.getAssignments().add(assignment);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
        return assignmentList;
    }

    public void addAnnouncementToDatabase(AnnouncementMgmtUI announcementMgmtUI) {
        openConnection();
        Announcement announcementToAdd = announcementMgmtUI.getNewAnnouncement();

        try {
            String query = "INSERT INTO AnnouncementTable (courseid, announcementtitle, announcementbody) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, announcementToAdd.getCourseTableID());
            pstmt.setString(2, announcementToAdd.getPageTitle());
            pstmt.setString(3, announcementToAdd.getAnnouncementContent());

            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void editAnnouncementInDatabase(Announcement announcement){
        openConnection();
        int announcementID = announcement.getAnnouncementID();
        String announcementTitle = announcement.getPageTitle();
        String announcementBody = announcement.getAnnouncementContent();
        try{
            String query = "UPDATE AnnouncementTable "
                    + "SET AnnouncementTable.announcementtitle = ?, AnnouncementTable.announcementbody = ? "
                    + "WHERE AnnouncementTable.ID = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, announcementTitle);
            pstmt.setString(2, announcementBody);
            pstmt.setInt(3, announcementID);
            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void deleteAnnouncementFromDatabase(Announcement announcement){
        openConnection();
        int announcementID = announcement.getAnnouncementID();

        try{
            String query = "DELETE FROM AnnouncementTable "
                    + "WHERE ID = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, announcementID);
            pstmt.executeUpdate();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public QuestionList getQuestionsByAssignment(Assignment assignment){
        int assignmentID = assignment.getAssignmentID();
        QuestionList questionList = new QuestionList();
        try
        {
            String query = "SELECT QuestionTable.ID, QuestionTable.Question, QuestionTable.PointValue, QuestionTable.assignmentid, QuestionTable.questiontype "
                    + "FROM QuestionTable "
                    + "WHERE QuestionTable.assignmentid = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, assignmentID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int questionID = rs.getInt("ID");
                String question = rs.getString("Question");
                int pointValue = rs.getInt("PointValue");
                int questionType = rs.getInt("questiontype");

                if (questionType == 1)
                {
                    MultipleChoiceQuestion multiQuestion = new MultipleChoiceQuestion(question, pointValue);
                    multiQuestion.setQuestionID(questionID);
                    multiQuestion.setAssignmentID(assignmentID);
                    multiQuestion.setQuestionType(questionType);

                    multiQuestion.setAnswerList(getAnswersByQuestion(multiQuestion));

                    questionList.addToList(multiQuestion);
                }
                else if (questionType == 2)
                {
                    OpenEndedQuestion openEndedQuestion = new OpenEndedQuestion(question, pointValue);
                    openEndedQuestion.setQuestionID(questionID);
                    openEndedQuestion.setAssignmentID(assignmentID);
                    openEndedQuestion.setQuestionType(questionType);

                    openEndedQuestion.setAnswerList(getAnswersByQuestion(openEndedQuestion));

                    questionList.addToList(openEndedQuestion);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return questionList;
    }

    public AnswerList getAnswersByQuestion(Question question){
        AnswerList answerList = new AnswerList();
        int questionID = question.getQuestionID();

        try
        {
            String query = "SELECT AnswerTable.ID, AnswerTable.Answer, AnswerTable.isCorrect "
                    + "FROM AnswerTable "
                    + "WHERE AnswerTable.questionid = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, questionID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("ID");
                String answer = rs.getString("Answer");
                Boolean isCorrect = rs.getBoolean("isCorrect");

                if (question.getClass().equals(MultipleChoiceQuestion.class))
                {
                    MultipleChoiceAnswer multiAnswer = new MultipleChoiceAnswer(answer);
                    multiAnswer.setAnswerID(id);

                    if (isCorrect)
                    {
                        multiAnswer.markCorrect();
                    }
                    else
                    {
                        multiAnswer.markIncorrect();
                    }

                    answerList.addToList(multiAnswer);
                }
                else if (question.getClass().equals(OpenEndedQuestion.class))
                {
                    OpenEndedAnswer openEndedAnswer = new OpenEndedAnswer(answer);
                    openEndedAnswer.setAnswerID(id);
                    openEndedAnswer.markCorrect();

                    answerList.addToList(openEndedAnswer);
                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return answerList;
    }

    public void closeConnection()
    {
        try
        {
            connection.close();
        }
        catch (Exception ee)
        {
            System.out.println(ee);
        }
    }
}
