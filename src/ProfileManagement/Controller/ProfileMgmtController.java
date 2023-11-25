package ProfileManagement.Controller;

import ProfileManagement.Model.Student;
import ProfileManagement.View.ProfileMgmtUI;
import UserAuthentication.Controller.HomepageController;
import UserAuthentication.Model.User;

public class ProfileMgmtController {
    private ProfileMgmtUI profileMgmtUI;
    private HomepageController homepageController;
    private User newUser;
    private Student student;

    /**
     * Constructor for the user management interface
     * @param u1 Student user profile to be managed through interface
     */
    public ProfileMgmtController(HomepageController homepageController) {
        this.homepageController = homepageController;
        newUser = new User("", "");
        this.profileMgmtUI = new ProfileMgmtUI(this);
    }

    public void setStudentMgmtUI(ProfileMgmtUI profileMgmtUI) {
        this.profileMgmtUI = profileMgmtUI;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ProfileMgmtUI getStudentMgmtUI() {
        return profileMgmtUI;
    }
}
