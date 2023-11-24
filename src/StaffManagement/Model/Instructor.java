package StaffManagement.Model;

import CourseManagement.Model.Course;
import CourseworkManagement.Model.Assignment;
import UserAuthentication.Model.User;

import java.util.ArrayList;

public class Instructor extends User {
    public ArrayList<Course> coursesTeaching;

    /**
     * Super constructor for Instructor User class
     * @param userLogin Given login username for authentication
     * @param userPassword Given login password for authentication
     * @param roleID Given user role for user
     */
    public Instructor(String userName, String userLogin, String userPassword, String roleID) {
        super(userName, userLogin, userPassword, roleID);
    }
    /**
     * Constructor for Instructor class
     * @param userLogin Given login username for authentication
     * @param userPassword Given login password for authentication
     * @param roleID Given user role for user
     * @param teaching courses actively teaching
     */
    public Instructor(String userName, String userLogin, String userPassword, String roleID, ArrayList<Course> teaching) {
        super(userName, userLogin, userPassword, roleID);
        this.coursesTeaching = teaching;
    }
}
