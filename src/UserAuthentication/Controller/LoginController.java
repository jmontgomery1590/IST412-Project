package UserAuthentication.Controller;

import StaffManagement.Controller.StaffMgmtController;
import StudentManagement.Controller.StudentMgmtController;
import UserAuthentication.View.HomepageUI;
import UserAuthentication.View.LoginUI;
import UserAuthentication.Model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private User u1;
    private LoginUI loginUI;
    private HomepageUI homepageUI;
    private String userName;
    private String loginID;
    private String password;
    private String roleID;
    private StudentMgmtController studentCntrl;
    private StaffMgmtController staffMgmtCntrl;
    private HomepageController homepageCntrl;


    public LoginController() {
        this.setLoginUI(new LoginUI(this));
        addALButtons();
        //this.setU1(this.getLoginInt().Login());
    }

    /**
     * Helps point the buttons using actionlistener
     */
    public void addALButtons(){
        this.getLoginUI().getLoginButton().addActionListener(this);
        this.getLoginUI().getExitButton().addActionListener(this);
    }

    /**
     * Recognizes user's button click and checks for which button
     * was clicked and acts accordingly
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        // gathers the user's input and passes to User Model class to verify password
        if (obj == this.getLoginUI().getLoginButton()){
            this.setLoginID(this.getLoginUI().getUsernameField().getText());
            this.setPassword("");
            for (char character : this.getLoginUI().getPasswordField().getPassword()) {
                if (this.getPassword().isEmpty()){
                    this.setPassword(Character.toString(character));
                } else {
                    this.setPassword(this.getPassword() + character);
                }
            }
            this.setU1(new User(this.getUserName(), this.getLoginID(), this.getPassword(), this.getRoleID()));

            // if successful, initiate homepage and close the login window
            if (this.getU1().verifyUser()){
                this.setHomepageCntrl(new HomepageController(this));
                this.getLoginUI().getLoginFrame().dispose();

                // if unsuccessful, alert user and reset the text entry/password fields
            } else {
                this.getLoginUI().getUsernameField().setText("");
                this.getLoginUI().getPasswordField().setText("");
                this.getLoginUI().getUsernameField().setBackground(Color.red);
                this.getLoginUI().getPasswordField().setBackground(Color.red);
            }
        } else if (obj == this.getLoginUI().getExitButton()){
            System.exit(0);
        }
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
