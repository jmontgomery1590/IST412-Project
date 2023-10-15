package StaffManagement.Model;

import CourseManagement.Model.Course;
import UserAuthentication.User;

import java.util.ArrayList;

public class Admin extends User {
    public ArrayList<Instructor> instructorsOverseen;
    public ArrayList<Course> masterCourseList;

    /**
     * Super constructor for Admin User class
     * @param userLogin Given login username for authentication
     * @param userPassword Given login password for authentication
     */
    public Admin(String userLogin, String userPassword, String roleID) {
        super(userLogin, userPassword);
        this.setRoleID(roleID);
    }

    /**
     * Constructor for Admin class
     * @param userLogin Given login username for authentication
     * @param userPassword Given login password for authentication
     * @param overseen List of instructors overseen
     */
    public Admin(String userLogin, String userPassword, ArrayList<Instructor> overseen) {
        super(userLogin, userPassword);
        this.instructorsOverseen = overseen;
    }

    /**
     * Set user role
     * @param id Role ID level to assign to user.
     */
    public void setRoleID(User u1, String id) {
        this.roleID = id;
    }

    /**
     * Assign instructor to given course
     * @param courseId Course id to assign instructor to
     * @param i1 Instructor to be assigned to course
     */
    public void assignCourseInstructor(String courseId, Instructor i1) {}


}
