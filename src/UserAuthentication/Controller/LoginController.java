package UserAuthentication.Controller;

import StaffManagement.Controller.StaffMgmtController;
import StudentManagement.StudentMgmtController;
import UserAuthentication.View.HomepageUI;
import UserAuthentication.View.LoginInterface;
import UserAuthentication.Model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

// test update
public class LoginController implements ActionListener {
    private User u1;
    private LoginInterface loginInt;
    private HomepageUI homepageUI;
    private String username;
    private String password;
    private StudentMgmtController studentCntrl;
    private StaffMgmtController staffMgmtCntrl;
    private HomepageController homepageCntrl;


    public LoginController() {
        this.setLoginInt(new LoginInterface(this));
        addALButtons();
        //this.setU1(this.getLoginInt().Login());
    }

    /**
     * Helps point the buttons using actionlistener
     */
    public void addALButtons(){
        this.getLoginInt().getLoginButton().addActionListener(this);
        this.getLoginInt().getExitButton().addActionListener(this);
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
        if (obj == this.getLoginInt().getLoginButton()){
            this.setUsername(this.getLoginInt().getUsernameField().getText());
            this.setPassword("");
            for (char character : this.getLoginInt().getPasswordField().getPassword()) {
                if (this.getPassword().isEmpty()){
                    this.setPassword(Character.toString(character));
                } else {
                    this.setPassword(this.getPassword() + character);
                }
            }
            this.setU1(new User(this.getUsername(), this.getPassword()));

            // if successful, initiate homepage and close the login window
            if (this.getU1().verifyUser()){
                this.setHomepageCntrl(new HomepageController(this));
                this.getLoginInt().getLoginFrame().dispose();

                // if unsuccessful, alert user and reset the text entry/password fields
            } else {
                this.getLoginInt().getUsernameField().setText("");
                this.getLoginInt().getPasswordField().setText("");
                this.getLoginInt().getUsernameField().setBackground(Color.red);
                this.getLoginInt().getPasswordField().setBackground(Color.red);
            }
        } else if (obj == this.getLoginInt().getExitButton()){
            System.exit(0);
        }
    }

    /* public void loginTestMethod(){
        this.getU1().setRoleID(this.getU1().verifyUser());

        switch (this.getU1().getRoleID()){
            case "STUDENT":
                this.studentCntrl = new StudentMgmtController(this.getU1());
                this.setHomepageUI(new HomepageUI());
                this.getLoginInt().dispose();
                break;
            case "INSTRUCTOR", "ADMIN", "TA":
                this.staffMgmtCntrl = new StaffMgmtController(this.getU1());
                this.setHomepageUI(new HomepageUI());
                this.getLoginInt().dispose();
                break;
            default:
                break;
        }
    }
     */

    public LoginInterface getLoginInt() {
        return loginInt;
    }

    public void setLoginInt(LoginInterface loginInt) {
        this.loginInt = loginInt;
    }

    public User getU1() {
        return u1;
    }

    public void setU1(User u1) {
        this.u1 = u1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StudentMgmtController getStudentCntrl() {
        return studentCntrl;
    }

    public void setStudentCntrl(StudentMgmtController studentCntrl) {
        this.studentCntrl = studentCntrl;
    }

    public StaffMgmtController getStaffMgmtCntrl() {
        return staffMgmtCntrl;
    }

    public void setStaffMgmtCntrl(StaffMgmtController staffMgmtCntrl) {
        this.staffMgmtCntrl = staffMgmtCntrl;
    }

    public HomepageUI getHomepageUI() {
        return homepageUI;
    }

    public void setHomepageUI(HomepageUI homepageUI) {
        this.homepageUI = homepageUI;
    }

    public HomepageController getHomepageCntrl() {
        return homepageCntrl;
    }

    public void setHomepageCntrl(HomepageController homepageCntrl) {
        this.homepageCntrl = homepageCntrl;
    }
}
