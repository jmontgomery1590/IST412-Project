package ProfileManagement.Controller;

import UserAuthentication.Model.Student;
import ProfileManagement.View.ProfileMgmtUI;
import UserAuthentication.Controller.HomepageController;
import UserAuthentication.Model.User;

public class ProfileMgmtController {
    private ProfileMgmtUI profileMgmtUI;
    private HomepageController homepageController;
    private User user;
    private Student student;


    /**
     * Constructor for the user management interface
     * @param user Student user profile to be managed through interface
     */
    public ProfileMgmtController(HomepageController homepageController) {
        this.homepageController = homepageController;
        user = homepageController.getUser();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
