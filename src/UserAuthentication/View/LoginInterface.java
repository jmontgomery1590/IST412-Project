package UserAuthentication.View;

import UserAuthentication.Controller.LoginController;
import UserAuthentication.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class LoginInterface extends JFrame {
    private JFrame loginFrame;
    private JPanel loginPanel, buttonPanel, passwordPanel, userNamePanel;
    private JPanel titlePanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, exitButton;
    private JLabel loginTextLabel, userNameLabel, passwordLabel, loginRequestLabel;
    private LoginController loginController;

    public LoginInterface(LoginController loginController) {
        this.setLoginController(loginController);
        this.setLoginFrame(new JFrame("LMS Login"));
        this.getLoginFrame().add(this.getLoginPanel());
        this.getLoginTextLabel().setForeground(Color.WHITE);
        this.getLoginRequestLabel().setForeground(Color.WHITE);
        this.getUserNameLabel().setForeground(Color.WHITE);
        this.getPasswordLabel().setForeground(Color.WHITE);
        this.getLoginFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getLoginFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getLoginFrame().setUndecorated(true);
        this.getLoginFrame().setVisible(true);
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JLabel getLoginTextLabel() {
        return loginTextLabel;
    }

    public JLabel getUserNameLabel() {
        return userNameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public JFrame getLoginFrame() {
        return loginFrame;
    }

    public void setLoginFrame(JFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public JLabel getLoginRequestLabel() {
        return loginRequestLabel;
    }
}