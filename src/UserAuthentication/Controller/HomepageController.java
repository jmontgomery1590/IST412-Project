package UserAuthentication.Controller;

import CourseManagement.Controller.CourseMgmtController;
import CourseworkManagement.Controller.CourseworkMgmtController;
import UserAuthentication.Model.User;
import UserAuthentication.View.HomepageUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomepageController implements ActionListener {
    private User user;
    private HomepageUI homepageUI;
    private LoginController loginController;
    private CourseMgmtController courseMgmtCntrl;


    public HomepageController(LoginController loginController){
        this.setUser(loginController.getU1());
        this.setHomepageUI(new HomepageUI(this));
        addALButtons();
    }

    /**
     * Helps point the buttons using actionlistener
     */
    public void addALButtons(){
        this.getHomepageUI().getHomeButton().addActionListener(this);
        this.getHomepageUI().getCoursesButton().addActionListener(this);
        this.getHomepageUI().getProfileButton().addActionListener(this);
        this.getHomepageUI().getExitButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == this.getHomepageUI().getCoursesButton()){
            this.setCourseMgmtCntrl(new CourseMgmtController(this));
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HomepageUI getHomepageUI() {
        return homepageUI;
    }

    public void setHomepageUI(HomepageUI homepageUI) {
        this.homepageUI = homepageUI;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}
