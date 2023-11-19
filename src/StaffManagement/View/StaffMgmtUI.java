package StaffManagement.View;

import UserAuthentication.Model.User;

public class StaffMgmtUI {
    public StaffMgmtUI() {}

    /**
     * Displays the chosen user interface
     * @param u1 User to be displayed in management interface
     */
    public void displayProfile(User u1, int accessLevel) {
        System.out.println("Username: " + u1.getLoginID().toLowerCase());
        System.out.println("User Role: " + u1.getRoleID());
        System.out.println("Access Level: " + accessLevel);
    }


}
