package StudentManagement;

import CourseManagement.Controller.CourseMgmtController;
import UserAuthentication.Model.User;

public class StudentMgmtController {
    private StudentMgmtUI si;
    private CourseMgmtController courseMgmtController;
    private Student student;

    /**
     * Constructor for the user management interface
     * @param u1 Student user profile to be managed through interface
     */
    public StudentMgmtController (User u1) {
        this.setSi(new StudentMgmtUI());
        this.setStudent(new Student(u1.getLoginID(), u1.getPassword(), u1.getRoleID()));
        this.getSi().displayProfile(this.getStudent());

        // interact with courses
        //this.setCourseMgmtController(new CourseMgmtController(this));
    }

    public StudentMgmtUI getSi() {
        return si;
    }

    public void setSi(StudentMgmtUI si) {
        this.si = si;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseMgmtController getCourseMgmtController() {
        return courseMgmtController;
    }

    public void setCourseMgmtController(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
    }
}
