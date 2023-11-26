package UserAuthentication.Model;

import CourseManagement.Model.Course;
import UserAuthentication.Model.User;

import java.util.ArrayList;

public class TA extends User {
    public ArrayList<Course> coursesTutored;

    /**
     * Super constructor for TA User class
     * @param userLogin Given login username for authentication
     * @param userPassword Given login password for authentication
     */
    public TA(String userLogin, String userPassword) {
        super(userLogin, userPassword);
    }

    /**
     * Constructor for TA class
     * @param userLogin Given login username for authentication
     * @param userPassword Given login password for authentication
     * @param tutoring Courses actively tutoring students in
     */
    public TA(String userLogin, String userPassword, ArrayList<Course> tutoring) {
        super(userLogin, userPassword);
        this.coursesTutored = tutoring;
    }
}
