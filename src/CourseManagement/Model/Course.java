package CourseManagement.Model;

import StaffManagement.Model.Instructor;

import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseName;
    private String maxEnrolled;
    private ArrayList<Page> coursePages;
    private Instructor instructor;

    public Course(String id, String courseName, String maxEnrolled) {
        this.courseID = id;
        this.courseName = courseName;
        this.maxEnrolled = maxEnrolled;
        this.setCoursePages(new ArrayList<>());
    }

    public String getCourseName() {
        return this.courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMaxEnrolled() {
        return maxEnrolled;
    }

    public void setMaxEnrolled(String maxEnrolled) {
        this.maxEnrolled = maxEnrolled;
    }

    public ArrayList<Page> getCoursePages() {
        return coursePages;
    }

    public void setCoursePages(ArrayList<Page> coursePages) {
        this.coursePages = coursePages;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
