package UserAuthentication.Model;

public class User {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String roleID;
    private String successful = "\n---Login Successful---\n";

    /**
     * Constructor for User class
     * @param userLogin given username for User
     * @param userPassword given password for User
     */
    public User(String userLogin, String userPassword) {
        userName = userLogin;
        password = userPassword;
    }

    /**
     * Verify a user profile exists
     */
    public Boolean verifyUser() {
        if (userName.equalsIgnoreCase("Student") && password.equals("Student123")) {
            System.out.println(successful);
            return true;
        } else if (userName.equalsIgnoreCase("Instructor") && password.equals("Instructor123")) {
            System.out.println(successful);
            return true;
        } else if (userName.equalsIgnoreCase("Admin") && password.equals("Admin123")) {
            System.out.println(successful);
            return true;
        } else if (userName.equalsIgnoreCase("TA") && password.equals("TA123")) {
            System.out.println(successful);
            return true;
        } else return false;
    }

    public String getUserName() {
        return userName;
    }

    public String getLoginID() {
        return userName;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuccessful() {
        return successful;
    }

    public void setSuccessful(String successful) {
        this.successful = successful;
    }
}
