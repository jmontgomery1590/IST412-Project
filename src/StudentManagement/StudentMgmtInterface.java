package StudentManagement;

import UserAuthentication.User;

public class StudentMgmtInterface {
    public StudentMgmtInterface() {}

    /**
     * Displays the chosen user interface
     * @param u1 User to be displayed in management interface
     */
    public void displayProfile(User u1) {

        System.out.println("Username: " + u1.getLoginID().toLowerCase());
        System.out.println("User Role: " + u1.getRoleID());
    }
}
