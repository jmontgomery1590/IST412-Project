package UserAuthentication.Controller;

import DatabaseMgmt.DatabaseConnection;
import UserManagement.Controller.UserMgmtController;
import ProfileManagement.Controller.ProfileMgmtController;
import UserAuthentication.View.HomepageUI;
import UserAuthentication.View.LoginUI;
import UserAuthentication.Model.User;

public class LoginController {
    private User u1;
    private LoginUI loginUI;
    private HomepageUI homepageUI;
    private String userName;
    private String loginID;
    private String password;
    private String roleID;
    private ProfileMgmtController studentCntrl;
    private UserMgmtController staffMgmtCntrl;
    private HomepageController homepageCntrl;
    private DatabaseConnection database;


    public LoginController() {
        this.setLoginUI(new LoginUI(this));
        database = new DatabaseConnection();
    }

    public boolean verifyUser(){
        return database.getUserLoginInfo(this);
    }
    public LoginUI getLoginUI() {
        return loginUI;
    }

    public void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

    public User getU1() {
        return u1;
    }

    public void setU1(User u1) {
        this.u1 = u1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public HomepageUI getHomepageUI() {
        return homepageUI;
    }

    public void setHomepageCntrl(HomepageController homepageCntrl) {
        this.homepageCntrl = homepageCntrl;
    }
}
