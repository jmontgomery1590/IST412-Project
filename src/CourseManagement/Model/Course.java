package CourseManagement.Model;

import CourseworkManagement.Model.AssignmentList;
import UserAuthentication.Model.Instructor;

import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseName;
    private String maxEnrolled;
    private int courseTableID;
    private ArrayList<Page> coursePages;
    private Instructor instructor;
    private LessonList lessonList;
    private AssignmentList assignmentList;
    private AnnouncementList announcementList;

    public Course(int tableID, String id, String courseName, String maxEnrolled, Instructor instructor) {
        courseTableID = tableID;
        courseID = id;
        this.courseName = courseName;
        this.maxEnrolled = maxEnrolled;
        this.setCoursePages(new ArrayList<>());
        this.lessonList = new LessonList();
        this.assignmentList = new AssignmentList();
        this.announcementList = new AnnouncementList();
        this.instructor = instructor;
    }

    public int getCourseTableID() {
        return courseTableID;
    }

    public void setCourseTableID(int courseTableID) {
        this.courseTableID = courseTableID;
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

    public void setCoursePages(ArrayList<Page> coursePages) {
        this.coursePages = coursePages;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public LessonList getLessonList() {
        return lessonList;
    }

    public AssignmentList getAssignmentList() {
        return assignmentList;
    }

    public AnnouncementList getAnnouncementList() {
        return announcementList;
    }
}
