package UserAuthentication.View;

import UserAuthentication.Model.User;

import java.util.Scanner;

public class LoginInterface {

    public LoginInterface() {}

    public User Login() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Username:");
        String userName = scn.next();
        System.out.println("Password:");
        String password = scn.next();
        return new User(userName, password);
    }
}
