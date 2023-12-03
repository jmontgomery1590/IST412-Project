package UserAuthentication.Model;

import CourseManagement.Model.Course;
import CourseworkManagement.Model.AssignmentList;
import UserAuthentication.Model.User;
import java.util.ArrayList;

public class Student extends User {
    private String major;
    private ArrayList<Course> enrolledCourses;

    /**
     * Super constructor for Student User class
     * @param userLogin Given login username for authentication
     * @param password Given login password for authentication
     */
    public Student(String userLogin, String password) {
        super(userLogin, password);
        this.major = "Undecided";
        this.enrolledCourses = new ArrayList<>();
    }
}
