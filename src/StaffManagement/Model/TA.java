package StaffManagement.Model;

import CourseManagement.Model.Course;
import CourseworkManagement.Model.Assignment;
import UserAuthentication.Model.User;

import java.util.ArrayList;

public class TA extends User {
    public ArrayList<Course> coursesTutored;

    /**
     * Super constructor for TA User class
     * @param userLogin Given login username for authentication
     * @param userPassword Given login password for authentication
     * @param roleID Given user role for user
     */
    public TA(String userName, String userLogin, String userPassword, String roleID) {
        super(userName, userLogin, userPassword, roleID);
    }

    /**
     * Constructor for TA class
     * @param userLogin Given login username for authentication
     * @param userPassword Given login password for authentication
     * @param roleID Given user role for user
     * @param tutoring Courses actively tutoring students in
     */
    public TA(String userName, String userLogin, String userPassword, String roleID, ArrayList<Course> tutoring) {
        super(userName, userLogin, userPassword, roleID);
        this.coursesTutored = tutoring;
    }
}
