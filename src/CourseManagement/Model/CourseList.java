package CourseManagement.Model;

import java.util.ArrayList;

public class CourseList {
    private Course course;
    private ArrayList<Course> courses = new ArrayList<>();

    public CourseList(){
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
