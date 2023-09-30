package UserAuthentication;

public class User {
    private String loginID;
    private String password;
    private String userNum;
    private String userName;
    public String roleID;

    /**
     * Constructor for User class
     * @param userLogin given username for User
     * @param userPassword given password for User
     */
    public User(String userLogin, String userPassword) {
        this.loginID = userLogin;
        this.password = userPassword;
        this.roleID = "";
    }

    /**
     * Returns username for this User
     * @return String representing the username for the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Verify a user profile exists
     */
    public String verifyUser() {
        if (loginID.equalsIgnoreCase("Student") && password.equals("Student123")) {
            return "STUDENT";
        } else if (loginID.equalsIgnoreCase("Instructor") && password.equals("Instructor123")) {
            return "INSTRUCTOR";
        } else if (loginID.equalsIgnoreCase("Admin") && password.equals("Admin123")) {
            return "ADMIN";
        } else if (loginID.equalsIgnoreCase("ta") && password.equals("TA123")) {
            return "TA";
        } else return "Not Enrolled";
    }

    /**
     * Change password linked to User profile
     * @param loginName Given username to change password on
     * @param userPassword New password to set to user account.
     */
    public void changePassword(String loginName, String userPassword) {}

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
