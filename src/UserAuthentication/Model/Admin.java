package UserAuthentication.Model;

import CourseManagement.Model.Course;

import java.util.ArrayList;

public class Admin extends User {
    public ArrayList<Instructor> instructorsOverseen;
    public ArrayList<Course> masterCourseList;

    /**
     * Super constructor for Admin User class
     * @param userLogin Given login username for authentication
     * @param userPassword Given login password for authentication
     */
    public Admin(String userName, String userLogin, String userPassword, String roleID) {
        super(userLogin, userPassword);
    }

    /**
     * Constructor for Admin class
     * @param userLogin Given login username for authentication
     * @param userPassword Given login password for authentication
     * @param overseen List of instructors overseen
     */
    public Admin(String userName, String userLogin, String userPassword, String roleID, ArrayList<Instructor> overseen) {
        super(userLogin, userPassword);
        this.instructorsOverseen = overseen;
    }
}
