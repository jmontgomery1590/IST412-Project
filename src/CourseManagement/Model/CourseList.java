package CourseManagement.Model;

import StaffManagement.Model.Instructor;

import java.util.ArrayList;

public class CourseList {
    private Course course;
    private ArrayList<Course> courses = new ArrayList<>();

    public CourseList(){
        if (this.getCourses().isEmpty()){
            createCoursesList();
        }
    }

    /**
     * Testing method that generates quick courses for filling out table
     */
    public void createCoursesList(){
        for (int i = 0; i <= 5; i++){
            this.setCourse(new Course("IST 41" + i, "Course Name #" + i, 20));
            this.getCourse().setInstructor(new Instructor("Instructor " + i, "password", "Instructor"));
            this.getCourses().add(this.getCourse());
        }
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

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}