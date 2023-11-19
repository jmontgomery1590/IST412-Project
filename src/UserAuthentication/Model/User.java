package UserAuthentication.Model;

public class User {
    private String loginID;
    private String password;
    private String userNum;
    private String userName;
    public String roleID;
    private String successful = "\n---Login Successful---\n";

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
     * Verify a user profile exists
     */
    public Boolean verifyUser() {
        if (loginID.equalsIgnoreCase("Student") && password.equals("Student123")) {
            System.out.println(successful);
            return true;
        } else if (loginID.equalsIgnoreCase("Instructor") && password.equals("Instructor123")) {
            System.out.println(successful);
            return true;
        } else if (loginID.equalsIgnoreCase("Admin") && password.equals("Admin123")) {
            System.out.println(successful);
            return true;
        } else if (loginID.equalsIgnoreCase("TA") && password.equals("TA123")) {
            System.out.println(successful);
            return true;
        } else return false;
    }

    public String getLoginID() {
        return loginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
}
