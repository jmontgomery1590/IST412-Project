package ProfileManagement.View;

import ProfileManagement.Controller.ProfileMgmtController;
import UserAuthentication.Controller.HomepageController;
import UserAuthentication.Model.User;

import javax.swing.*;
import java.awt.*;

public class ProfileMgmtUI {
    private JPanel profileMgmtPanel, labelPanel, informationPanel;
    private JTextField loginTextField, passwordTextField, roleTextField;
    private JTextField usernameTextField;
    private JLabel loginIDLabel, passwordLabel, roleLabel, usernameLabel;
    private ProfileMgmtController profileMgmtCntrl;
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
}