package StaffManagement.View;

import UserAuthentication.User;

public class StaffMgmtInterface {
    public StaffMgmtInterface() {}

    /**
     * Displays the chosen user interface
     * @param u1 User to be displayed in management interface
     */
    public void displayProfile(User u1, int accessLevel) {
        System.out.println(u1.getLoginID());
        System.out.println(u1.getRoleID());
        System.out.println(accessLevel);
    }


}
