package StudentManagement.Model;

import CourseManagement.Model.Course;
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
    public Student(String userName, String userLogin, String password, String roleID) {
        super(userName, userLogin, password, roleID);
        this.major = "Undecided";
        this.enrolledCourses = new ArrayList<>();
    }

    /**
     * Returns an ArrayList of the enrolled courses
     * @return ArrayList representation of given courses
     */
    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    /**
     * Register for a course to enroll in
     * @param course Given course to register for
     */
    public void registerCourse(Course course) {
        this.enrolledCourses.add(course);
    }
}
