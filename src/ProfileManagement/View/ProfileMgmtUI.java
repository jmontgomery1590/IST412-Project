package ProfileManagement.View;

import ProfileManagement.Controller.ProfileMgmtController;

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

    public ProfileMgmtUI(ProfileMgmtController profileMgmtController) {
        profileMgmtCntrl = profileMgmtController;
        loginIDLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        roleLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);

        //usernameTextField.setText();
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