package UserAuthentication.Controller;

import CourseManagement.Controller.CourseMgmtController;
import CourseworkManagement.Controller.CourseworkMgmtController;
import StudentManagement.Controller.StudentMgmtController;
import UserAuthentication.Model.User;
import UserAuthentication.View.HomeViewUI;
import UserAuthentication.View.HomepageUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomepageController {
    private User user;
    private HomepageUI homepageUI;
    private LoginController loginController;
    private CourseMgmtController courseMgmtCntrl;
    private StudentMgmtController studentMgmtCntrl;
    private HomeViewUI homeViewUI;


    public HomepageController(LoginController loginController){
        this.loginController = loginController;
        this.setUser(loginController.getU1());
        this.setHomepageUI(new HomepageUI(this));
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

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }

    public HomeViewUI getHomeViewUI() {
        return homeViewUI;
    }

    public void setHomeViewUI(HomeViewUI homeViewUI) {
        this.homeViewUI = homeViewUI;
    }

    public StudentMgmtController getStudentMgmtCntrl() {
        return studentMgmtCntrl;
    }

    public void setStudentMgmtCntrl(StudentMgmtController studentMgmtCntrl) {
        this.studentMgmtCntrl = studentMgmtCntrl;
    }
}