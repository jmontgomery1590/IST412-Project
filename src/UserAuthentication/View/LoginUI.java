package UserAuthentication.View;

import UserAuthentication.Controller.HomepageController;
import UserAuthentication.Controller.LoginController;
import UserAuthentication.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {
    private JFrame loginFrame;
    private JPanel loginPanel, buttonPanel, passwordPanel, userNamePanel;
    private JPanel titlePanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, exitButton;
    private JLabel loginTextLabel, userNameLabel, passwordLabel, loginRequestLabel;
    private LoginController loginController;

    public LoginUI(LoginController loginController) {
        this.setLoginController(loginController);
        this.setLoginFrame(new JFrame("LMS Login"));
        this.getLoginFrame().add(this.getLoginPanel());
        this.getLoginTextLabel().setForeground(Color.WHITE);
        this.getLoginRequestLabel().setForeground(Color.WHITE);
        this.getUserNameLabel().setForeground(Color.WHITE);
        this.getPasswordLabel().setForeground(Color.WHITE);
        this.getLoginFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getLoginFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.getLoginFrame().setUndecorated(true);
        this.getLoginFrame().setVisible(true);
        addALButtons();
    }

    public void addALButtons(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passCredentialsForVerification())
                {
                    loginController.setHomepageCntrl(new HomepageController(loginController));
                    loginFrame.dispose();
                }
                else
                {
                    usernameField.setText("");
                    passwordField.setText("");
                    usernameField.setBackground(Color.red);
                    passwordField.setBackground(Color.red);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public boolean passCredentialsForVerification()
    {
        loginController.setLoginID(usernameField.getText());
        loginController.setPassword("");

        for (char character : getPasswordField().getPassword())
        {
            if (loginController.getPassword().isEmpty())
            {
                loginController.setPassword(Character.toString(character));
            }
            else
            {
                loginController.setPassword(loginController.getPassword() + character);
            }
        }

        loginController.setU1(new User(loginController.getLoginID(), loginController.getPassword()));
        return loginController.verifyUser();

        //return loginController.getU1().verifyUser();
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
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