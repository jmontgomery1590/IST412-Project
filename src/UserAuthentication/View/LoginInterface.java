package UserAuthentication.View;

import UserAuthentication.Controller.LoginController;
import UserAuthentication.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class LoginInterface extends JFrame {

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
        this.getLoginFrame().setVisible(true);
    }


    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(JPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JLabel getLoginTextLabel() {
        return loginTextLabel;
    }

    public void setLoginTextLabel(JLabel loginTextLabel) {
        this.loginTextLabel = loginTextLabel;
    }

    public JLabel getUserNameLabel() {
        return userNameLabel;
    }

    public void setUserNameLabel(JLabel userNameLabel) {
        this.userNameLabel = userNameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JPanel getPasswordPanel() {
        return passwordPanel;
    }

    public void setPasswordPanel(JPanel passwordPanel) {
        this.passwordPanel = passwordPanel;
    }

    public JPanel getUserNamePanel() {
        return userNamePanel;
    }

    public void setUserNamePanel(JPanel userNamePanel) {
        this.userNamePanel = userNamePanel;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public LoginController getLoginController() {
        return loginController;
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

    public void setLoginRequestLabel(JLabel loginRequestLabel) {
        this.loginRequestLabel = loginRequestLabel;
    }

    private JFrame loginFrame;
    private LoginController loginController;
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;
    private JLabel loginTextLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JPanel buttonPanel;
    private JPanel passwordPanel;
    private JPanel userNamePanel;
    private JPanel titlePanel;
    private JLabel loginRequestLabel;
}
