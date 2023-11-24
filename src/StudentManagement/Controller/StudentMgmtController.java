package StudentManagement.Controller;

import CourseManagement.Controller.CourseMgmtController;
import StudentManagement.Model.Student;
import StudentManagement.View.StudentMgmtUI;
import UserAuthentication.Controller.HomepageController;
import UserAuthentication.Model.User;

public class StudentMgmtController {
    private StudentMgmtUI studentMgmtUI;
    private HomepageController homepageController;
    private User newUser;
    private Student student;

    /**
     * Constructor for the user management interface
     * @param u1 Student user profile to be managed through interface
     */
    public StudentMgmtController (HomepageController homepageController) {
        this.homepageController = homepageController;
        newUser = new User("", "", "", "");
        this.studentMgmtUI = new StudentMgmtUI(this);
    }

    public void setStudentMgmtUI(StudentMgmtUI studentMgmtUI) {
        this.studentMgmtUI = studentMgmtUI;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentMgmtUI getStudentMgmtUI() {
        return studentMgmtUI;
    }
}
