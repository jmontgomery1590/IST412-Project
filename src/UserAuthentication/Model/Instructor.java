package UserAuthentication.Model;

public class Instructor extends User {
    private int userIDNumber;
    private String userName;
    private String firstName;
    private String lastName;
    private String roleID;

    /**
     * Super constructor for Instructor User class
     * @param userName Given login username for authentication
     * @param userPassword Given login password for authentication
     * @param roleID Given user role for user
     */
    public Instructor(int userIDNumber, String userName, String firstName, String lastName, String userPassword, String roleID) {
        super(userName, userPassword);
        this.userIDNumber = userIDNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleID = roleID;
    }

    @Override
    public int getUserIDNumber() {
        return userIDNumber;
    }

    @Override
    public void setUserIDNumber(int userIDNumber) {
        this.userIDNumber = userIDNumber;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getRoleID() {
        return roleID;
    }

    @Override
    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
}
