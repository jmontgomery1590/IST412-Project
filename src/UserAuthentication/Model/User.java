package UserAuthentication.Model;

public class User {
    private int userIDNumber;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String roleID;

    /**
     * Constructor for User class
     * @param userLogin given username for User
     * @param userPassword given password for User
     */
    public User(String userLogin, String userPassword) {
        userName = userLogin;
        password = userPassword;
    }
    public String getUserName() {
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

    public int getUserIDNumber() {
        return userIDNumber;
    }

    public void setUserIDNumber(int userIDNumber) {
        this.userIDNumber = userIDNumber;
    }
}
