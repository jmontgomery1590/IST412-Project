package UserManagement.View;

import UserAuthentication.Model.User;

public class UserMgmtUI {
    public UserMgmtUI() {}

    /**
     * Displays the chosen user interface
     * @param u1 User to be displayed in management interface
     */
    public void displayProfile(User u1, int accessLevel) {
        System.out.println("Username: " + u1.getUserName().toLowerCase());
        System.out.println("User Role: " + u1.getRoleID());
        System.out.println("Access Level: " + accessLevel);
    }


}
