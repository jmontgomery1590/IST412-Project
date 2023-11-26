package DatabaseMgmt;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Announcement;
import CourseManagement.Model.Course;
import CourseManagement.Model.Lesson;
import UserManagement.Model.Instructor;
import UserAuthentication.Controller.LoginController;

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
                Course course = new Course(tableID, name, id, enrolledConverted, instructor);
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

    public void getCourseLessonList(CourseMgmtController courseMgmtController) {
        openConnection();
        try {
            int courseID = courseMgmtController.getSelectedCourse().getCourseTableID();

            String query = "SELECT LessonTable.lessontitle, LessonTable.lessoncontent, LessonTable.assignedreading "
                    + "FROM LessonTable "
                    + "WHERE LessonTable.courseid = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, courseID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                String lessonTitle = rs.getString("lessontitle");
                String lessonContent = rs.getString("lessoncontent");
                String assignedReading = rs.getString("assignedreading");

                Lesson lesson = new Lesson(lessonTitle, lessonContent, assignedReading);
                courseMgmtController.getLessonList().getLessons().add(lesson);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void getCourseAnnouncementList(CourseMgmtController courseMgmtController) {
        openConnection();
        try {
            int courseID = courseMgmtController.getSelectedCourse().getCourseTableID();

            String query = "SELECT AnnouncementTable.announcementtitle, AnnouncementTable.announcementbody "
                    + "FROM AnnouncementTable "
                    + "WHERE AnnouncementTable.courseid = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, courseID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                String announcementTitle = rs.getString("announcementtitle");
                String announcementBody = rs.getString("announcementbody");

                Announcement announcement = new Announcement(announcementTitle, announcementBody);
                courseMgmtController.getAnnouncementList().getAnnouncements().add(announcement);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
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
