package ProfileManagement.View;

import ProfileManagement.Controller.ProfileMgmtController;
import UserAuthentication.Controller.HomepageController;
import UserAuthentication.Model.User;

import javax.swing.*;
import java.awt.*;

public class ProfileMgmtUI {
    private JPanel profileMgmtPanel;
    private JTextField loginTextField;
    private JTextField passwordTextField;
    private JTextField roleTextField;
    private JPanel labelPanel;
    private JPanel informationPanel;
    private JTextField usernameTextField;
    private JLabel loginIDLabel;
    private JLabel passwordLabel;
    private JLabel roleLabel;
    private JLabel usernameLabel;
    private ProfileMgmtController profileMgmtCntrl;
    private HomepageController homepageController;
    private User user;

    public ProfileMgmtUI(ProfileMgmtController profileMgmtController) {
        profileMgmtCntrl = profileMgmtController;
        user = profileMgmtController.getUser();
        setProfileText();
    }

    public void setProfileText() {
        usernameTextField.setText(user.getFirstName() + " " + user.getLastName());
        usernameTextField.setEditable(false);
        loginTextField.setText(user.getUserName());
        loginTextField.setEditable(false);
        passwordTextField.setText(user.getPassword());
        passwordTextField.setEditable(false);
        roleTextField.setText(user.getRoleID());
        roleTextField.setEditable(false);
    }

    public JPanel getStudentMgmtPanel() {
        return profileMgmtPanel;
    }

    public void setStudentMgmtPanel(JPanel studentMgmtPanel) {
        this.profileMgmtPanel = studentMgmtPanel;
    }

    public JTextField getLoginTextField() {
        return loginTextField;
    }

    public void setLoginTextField(JTextField loginTextField) {
        this.loginTextField = loginTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(JTextField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public JTextField getRoleTextField() {
        return roleTextField;
    }

    public void setRoleTextField(JTextField roleTextField) {
        this.roleTextField = roleTextField;
    }

    public JPanel getLabelPanel() {
        return labelPanel;
    }

    public void setLabelPanel(JPanel labelPanel) {
        this.labelPanel = labelPanel;
    }

    public JPanel getInformationPanel() {
        return informationPanel;
    }

    public void setInformationPanel(JPanel informationPanel) {
        this.informationPanel = informationPanel;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public ProfileMgmtController getStudentMgmtCntrl() {
        return profileMgmtCntrl;
    }

    public void setStudentMgmtCntrl(ProfileMgmtController studentMgmtCntrl) {
        this.profileMgmtCntrl = studentMgmtCntrl;
    }
}