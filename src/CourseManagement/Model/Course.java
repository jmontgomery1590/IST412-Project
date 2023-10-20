package CourseManagement.Model;

import StaffManagement.Model.Instructor;

import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseName;
    private int maxEnrolled;
    private ArrayList<Page> coursePages;
    private Instructor instructor;

    public Course(String id, String courseName, int maxEnrolled) {
        this.courseID = id;
        this.courseName = courseName;
        this.maxEnrolled = maxEnrolled;
        this.setCoursePages(new ArrayList<>());
    }

    /**
     * Create new course
     * @param c1 Course to be created
     */
    public void createCourse(Course c1) {}

    /**
     * Adds a new page to the course
     * @param courseId course to add the page to
     * @param p1 page to be added to the course
     */
    public void addPage(String courseId, Page p1) {
    }

    /**
     * Returns the course name for this course
     * @return String representing the name for the course.
     */
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

    public int getMaxEnrolled() {
        return maxEnrolled;
    }

    public void setMaxEnrolled(int maxEnrolled) {
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
