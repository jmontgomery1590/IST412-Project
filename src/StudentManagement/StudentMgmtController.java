package StudentManagement;

import UserAuthentication.User;

public class StudentMgmtController {
    private StudentMgmtInterface si;
    private Student student;

    /**
     * Constructor for the user management interface
     * @param u1 Student user profile to be managed through interface
     */
    public StudentMgmtController (User u1) {
        this.setSi(new StudentMgmtInterface());
        this.setStudent(new Student(u1.getLoginID(), u1.getPassword(), u1.getRoleID()));
        this.getSi().displayProfile(this.getStudent());
    }

    public StudentMgmtInterface getSi() {
        return si;
    }

    public void setSi(StudentMgmtInterface si) {
        this.si = si;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
