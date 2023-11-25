package DatabaseMgmt;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import StaffManagement.Model.Instructor;
import UserAuthentication.Controller.LoginController;

import java.sql.*;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection(){}


    public void openConnection()
    {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://C://Users//Joe//OneDrive//Documents//IntelliJProjects//IST412-Project//LMSDB.accdb");
        }
        catch (Exception ee)
        {
            System.out.println(ee);
        }
    }

    public void executeQuery(String query, int columnNumber)
    {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            StringBuilder stringBuilder = new StringBuilder();

            while (rs.next())
            {
                for (int i = 1; i <= columnNumber; i++) {
                    String column = rs.getString(i);
                    stringBuilder.append(column).append(" ");
                }
            }
            System.out.println(stringBuilder);
            connection.commit();
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

    public Instructor getInstructorForCourse(int instructorID)
    {
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

    public void getInstructorCourseList(CourseMgmtController courseMgmtController)
    {
        openConnection();
        int userID = courseMgmtController.getHomepageController().getUser().getUserIDNumber();
        try
        {
            String query = "SELECT CourseTable.courseid, CourseTable.coursename, CourseTable.maxenrolled, CourseTable.instructorid  "
                    + "FROM CourseTable "
                    + "WHERE CourseTable.instructorid = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                String id = rs.getString("courseid");
                String name = rs.getString("coursename");
                int enrolled = rs.getInt("maxenrolled");
                int instructorID = rs.getInt("instructorid");
                String enrolledConverted = String.valueOf(enrolled);
                Instructor instructor = getInstructorForCourse(instructorID);
                Course course = new Course(id, name, enrolledConverted, instructor);
                courseMgmtController.getCourseList().getCourses().add(course);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        closeConnection();
    }

    public void getAdminCourseList(CourseMgmtController courseMgmtController)
    {
        openConnection();
        try
        {
            String query = "SELECT CourseTable.courseid, CourseTable.coursename, CourseTable.maxenrolled, CourseTable.instructorid  "
                    + "FROM CourseTable ";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                String id = rs.getString("courseid");
                String name = rs.getString("coursename");
                int enrolled = rs.getInt("maxenrolled");
                int instructorID = rs.getInt("instructorid");
                String enrolledConverted = String.valueOf(enrolled);
                Instructor instructor = getInstructorForCourse(instructorID);
                Course course = new Course(id, name, enrolledConverted, instructor);
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
            String query = "SELECT CourseTable.courseid, CourseTable.coursename, CourseTable.maxenrolled, CourseTable.instructorid  "
                    + "FROM CourseTable "
                    + "JOIN StudentEnrolledTable ON CourseTable.ID = StudentEnrolledTable.courseid "
                    + "WHERE StudentEnrolledTable.userid = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                String id = rs.getString("courseid");
                String name = rs.getString("coursename");
                int enrolled = rs.getInt("maxenrolled");
                int instructorID = rs.getInt("instructorid");
                String enrolledConverted = String.valueOf(enrolled);
                Instructor instructor = getInstructorForCourse(instructorID);
                Course course = new Course(id, name, enrolledConverted, instructor);
                courseMgmtController.getCourseList().getCourses().add(course);
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
