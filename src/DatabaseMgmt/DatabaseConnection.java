package DatabaseMgmt;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Announcement;
import CourseManagement.Model.Course;
import CourseManagement.Model.Lesson;
import CourseManagement.View.AnnouncementMgmtUI;
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
                    + "WHERE CourseTable.instructorid = ? AND CourseTable.isactive = true";
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
                    + "FROM CourseTable "
                    + "WHERE CourseTable.isactive = true";
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
                    + "WHERE StudentEnrolledTable.userid = ? AND CourseTable.isactive = true";
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
            String query = "INSERT INTO CourseTable (courseid, coursename, maxenrolled, instructorid, isactive) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, courseToAdd.getCourseID());
            pstmt.setString(2, courseToAdd.getCourseName());
            pstmt.setInt(3, maxEnrolled);
            pstmt.setInt(4, instructorID);
            pstmt.setBoolean(5, true);

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

    public void disableCourseInDatabase(Course course) {
        openConnection();
        try {
            String query = "UPDATE CourseTable "
                    + "SET CourseTable.isactive = ? "
                    + "WHERE CourseTable.ID = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, course.getCourseTableID());
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
        AssignmentList assignmentList = new AssignmentList();

        for (Student student : students)
        {
            int userID = student.getUserIDNumber();
            try
            {
                String query = "SELECT AssignmentTable.ID, AssignmentTable.AssignmentName, StudentAssignmentTable.completed, StudentAssignmentTable.grade, StudentAssignmentTable.earnedscore "
                        + "FROM AssignmentTable "
                        + "JOIN StudentAssignmentTable ON AssignmentTable.ID = StudentAssignmentTable.assignmentid "
                        + "WHERE AssignmentTable.CourseID = ? AND StudentAssignmentTable.userid = ? AND AssignmentTable.isEnabled = ?";

                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, courseTableID);
                pstmt.setInt(2, userID);
                pstmt.setBoolean(3, true);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next())
                {
                    int assignmentID = rs.getInt("ID");
                    String assignmentTitle = rs.getString("AssignmentName");
                    boolean completed = rs.getBoolean("completed");
                    double earnedScore = rs.getDouble("earnedscore");
                    double grade = rs.getDouble("grade");

                    Assignment assignment = new Assignment(assignmentTitle);
                    assignment.setAssignmentID(assignmentID);
                    assignment.setAssignedStudent(student);
                    assignment.setQuestionList(getQuestionsByAssignment(assignment));
                    assignment.setCompleted(completed);
                    assignment.setEarnedScore(earnedScore);
                    assignment.setGrade(String.valueOf(grade));
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

    public AssignmentList getAssignmentsByCourseAlone(CourseworkMgmtController courseworkMgmtController) {
        openConnection();
        int courseTableID = courseworkMgmtController.getCurrentCourse().getCourseTableID();
        AssignmentList assignmentList = new AssignmentList();
        try
        {
            String query = "SELECT AssignmentTable.ID, AssignmentTable.AssignmentName "
                    + "FROM AssignmentTable "
                    + "WHERE AssignmentTable.CourseID = ? AND AssignmentTable.isEnabled = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, courseTableID);
            pstmt.setBoolean(2, true);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                int assignmentID = rs.getInt("ID");
                String assignmentTitle = rs.getString("AssignmentName");

                Assignment assignment = new Assignment(assignmentTitle);
                assignment.setAssignmentID(assignmentID);
                assignment.setQuestionList(getQuestionsByAssignment(assignment));
                assignment.setEarnedScore(0);
                assignment.setGrade(String.valueOf(0));
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

    public AssignmentList getStudentAssignmentByCourse(CourseworkMgmtController courseworkMgmtController) {
        openConnection();
        int courseTableID = courseworkMgmtController.getCurrentCourse().getCourseTableID();
        int userID = courseworkMgmtController.getCurrentStudent().getUserIDNumber();
        AssignmentList assignmentList = new AssignmentList();
        try
        {
            String query = "SELECT AssignmentTable.ID, AssignmentTable.AssignmentName, StudentAssignmentTable.completed, StudentAssignmentTable.grade, StudentAssignmentTable.earnedscore  "
                    + "FROM AssignmentTable "
                    + "JOIN StudentAssignmentTable ON AssignmentTable.ID = StudentAssignmentTable.assignmentid "
                    + "WHERE AssignmentTable.CourseID = ? AND StudentAssignmentTable.userid = ? AND AssignmentTable.isEnabled = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, courseTableID);
            pstmt.setInt(2, userID);
            pstmt.setBoolean(3, true);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                int assignmentID = rs.getInt("ID");
                String assignmentTitle = rs.getString("AssignmentName");
                boolean completed = rs.getBoolean("completed");
                double earnedScore = rs.getDouble("earnedscore");
                double grade = rs.getDouble("grade");

                Assignment assignment = new Assignment(assignmentTitle);
                assignment.setAssignmentID(assignmentID);
                assignment.setAssignedStudent(courseworkMgmtController.getCurrentStudent());
                assignment.setQuestionList(getQuestionsByAssignment(assignment));
                assignment.setCompleted(completed);
                assignment.setAssignedStudent(courseworkMgmtController.getCurrentStudent());
                assignment.setEarnedScore(earnedScore);
                assignment.setGrade(String.valueOf(grade));
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

    public void disableAssignmentFromDatabase(Assignment assignment) {
        openConnection();
        try
        {
            String query = "UPDATE AssignmentTable "
                + "SET AssignmentTable.isEnabled = ? "
                + "WHERE AssignmentTable.ID = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, assignment.getAssignmentID());
            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
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

    public void getSubmittedAnswer(Assignment assignment, Question question){
        try
        {
            String query = "SELECT StudentQuestionTable.submittedanswer "
                    + "FROM StudentQuestionTable "
                    + "WHERE StudentQuestionTable.questionid = ? AND StudentQuestionTable.userid = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, question.getQuestionID());
            pstmt.setInt(2, assignment.getAssignedStudent().getUserIDNumber());

            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                String submittedAnswer = rs.getString("submittedanswer");

                question.saveAnswer(submittedAnswer);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
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
                    if (assignment.getAssignedStudent() != null)
                    {
                        getSubmittedAnswer(assignment, multiQuestion);
                    }

                    questionList.addToList(multiQuestion);
                }
                else if (questionType == 2)
                {
                    OpenEndedQuestion openEndedQuestion = new OpenEndedQuestion(question, pointValue);
                    openEndedQuestion.setQuestionID(questionID);
                    openEndedQuestion.setAssignmentID(assignmentID);
                    openEndedQuestion.setQuestionType(questionType);
                    openEndedQuestion.setAnswerList(getAnswersByQuestion(openEndedQuestion));
                    if (assignment.getAssignedStudent() != null)
                    {
                        getSubmittedAnswer(assignment, openEndedQuestion);
                    }

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

    public void submitStudentAnswersToAssignmentQuestions(Assignment assignment){
        openConnection();
        for (Question question : assignment.getQuestionList().getQuestionList())
        {
            question.compareSubmittedAnswer();

            try
            {
                String query = "INSERT INTO StudentQuestionTable (questionid, userid, iscorrect, submittedanswer) "
                        + "VALUES (?, ?, ?,?)";

                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, question.getQuestionID());
                pstmt.setInt(2, assignment.getAssignedStudent().getUserIDNumber());
                pstmt.setBoolean(3, question.getIsCorrect());
                pstmt.setString(4, question.showSubmittedAnswer());

                pstmt.executeUpdate();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        markAssignmentCompleteAndGrade(assignment);
        closeConnection();
    }

    public void addAssignmentToDatabase(Assignment assignment, Course course) {
        openConnection();

        try
        {
            String query = "INSERT INTO AssignmentTable (CourseID, AssignmentName, isEnabled) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, course.getCourseTableID());
            pstmt.setString(2, assignment.getAssignmentTitle());
            pstmt.setBoolean(3, true);
            pstmt.executeUpdate();

            String query2 = "SELECT AssignmentTable.ID "
                    + "FROM AssignmentTable "
                    + "WHERE AssignmentTable.AssignmentName = ? AND AssignmentTable.isEnabled = ?";

            PreparedStatement pstmt2 = connection.prepareStatement(query2);
            pstmt2.setString(1, assignment.getAssignmentTitle());
            pstmt2.setBoolean(2, true);
            ResultSet rs = pstmt2.executeQuery();

            while (rs.next())
            {
                assignment.setAssignmentID(rs.getInt("ID"));
            }

            for (Student student : course.getStudentsEnrolled())
            {
                String query3 = "INSERT INTO StudentAssignmentTable (assignmentid, userid, completed, grade, earnedscore) "
                        + "VALUES (?, ?, ?, ?, ?)";

                PreparedStatement pstmt3 = connection.prepareStatement(query3);
                pstmt3.setInt(1, assignment.getAssignmentID());
                pstmt3.setInt(2, student.getUserIDNumber());
                pstmt3.setBoolean(3, false);
                pstmt3.setDouble(4, 0);
                pstmt3.setDouble(5, 0);

                pstmt3.executeUpdate();
            }
            addQuestionToDatabase(assignment);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void addQuestionToDatabase(Assignment assignment){
        try
        {
            for (Question question : assignment.getQuestionList().getQuestionList())
            {
                int questionType = 0;
                if (question.getClass().equals(MultipleChoiceQuestion.class))
                {
                    questionType = 1;
                }
                else if (question.getClass().equals(OpenEndedQuestion.class))
                {
                    questionType = 2;
                }

                String query = "INSERT INTO QuestionTable (Question, PointValue, assignmentid, questiontype) "
                        + "VALUES (?, ?, ?, ?)";

                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, question.getQuestion());
                pstmt.setDouble(2, question.getQuestionPointWorth());
                pstmt.setInt(3, assignment.getAssignmentID());
                pstmt.setInt(4, questionType);

                pstmt.executeUpdate();

                String query2 = "SELECT QuestionTable.ID "
                        + "FROM QuestionTable "
                        + "WHERE QuestionTable.Question = ? AND QuestionTable.assignmentid = ?";

                PreparedStatement pstmt2 = connection.prepareStatement(query2);
                pstmt2.setString(1, question.getQuestion());
                pstmt2.setInt(2, assignment.getAssignmentID());
                ResultSet rs = pstmt2.executeQuery();

                while (rs.next())
                {
                    question.setQuestionID(rs.getInt("ID"));
                }

                addAnswersToDatabase(question);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void addAnswersToDatabase(Question question){
        try
        {
            for (Answer answer : question.getAnswerList().getAnswerList())
            {
                String query = "INSERT INTO AnswerTable (Answer, isCorrect, questionid) "
                        + "VALUES (?, ?, ?)";

                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, answer.getAnswer());
                pstmt.setBoolean(2, answer.getIsCorrect());
                pstmt.setInt(3, question.getQuestionID());

                pstmt.executeUpdate();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void markAssignmentCompleteAndGrade(Assignment assignment){
        assignment.gradeAssignment();
        int assignmentID = assignment.getAssignmentID();
        int studentID = assignment.getAssignedStudent().getUserIDNumber();
        boolean completed = true;
        assignment.setCompleted(completed);
        double grade = Double.parseDouble(assignment.getGrade());
        double earnedScore = assignment.getEarnedScore();
        try
        {
            String query = "UPDATE StudentAssignmentTable "
                    + "SET completed = ?, grade = ?, earnedscore = ? "
                    + "WHERE StudentAssignmentTable.assignmentid = ? AND StudentAssignmentTable.userid = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, completed);
            pstmt.setDouble(2, grade);
            pstmt.setDouble(3, earnedScore);
            pstmt.setInt(4, assignmentID);
            pstmt.setInt(5, studentID);

            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println();
        }
    }

    public void updateAssignmentPointsEarnedAndGrade(Assignment assignment) {
        openConnection();
        int assignmentID = assignment.getAssignmentID();
        double grade = Double.parseDouble(assignment.getGrade());
        double earnedScore = assignment.getEarnedScore();
        int studentID = assignment.getAssignedStudent().getUserIDNumber();
        try
        {
            String query = "UPDATE StudentAssignmentTable "
                    + "SET grade = ?, earnedscore = ? "
                    + "WHERE StudentAssignmentTable.assignmentid = ? AND StudentAssignmentTable.userid = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDouble(1, grade);
            pstmt.setDouble(2, earnedScore);
            pstmt.setInt(3, assignmentID);
            pstmt.setInt(4, studentID);
            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void editAssignment(Assignment assignment){
        openConnection();
        try
        {
            String query = "UPDATE AssignmentTable "
                    + "SET AssignmentName = ? "
                    + "WHERE ID = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, assignment.getAssignmentTitle());
            pstmt.setInt(2, assignment.getAssignmentID());

            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        for (Question question : assignment.getQuestionList().getQuestionList())
        {
            editQuestion(question);
        }
        closeConnection();
    }

    public void editQuestion(Question question){
        try
        {
            String query = "UPDATE QuestionTable "
                    + "SET Question = ?, PointValue = ? "
                    + "WHERE ID = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, question.getQuestion());
            pstmt.setDouble(2, question.getQuestionPointWorth());
            pstmt.setInt(3, question.getQuestionID());

            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        for (Answer answer : question.getAnswerList().getAnswerList())
        {
            editAnswer(answer);
        }
    }

    public void editAnswer(Answer answer){
        try
        {
            String query = "UPDATE AnswerTable "
                    + "SET Answer = ?, isCorrect = ? "
                    + "WHERE ID = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, answer.getAnswer());
            pstmt.setBoolean(2, answer.getIsCorrect());
            pstmt.setInt(3, answer.getAnswerID());
            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
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
