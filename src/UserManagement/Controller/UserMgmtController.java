package UserManagement.Controller;

import CourseManagement.Controller.CourseMgmtController;
import UserManagement.Model.Admin;
import UserManagement.Model.Instructor;
import UserManagement.Model.TA;
import UserManagement.View.UserMgmtUI;
import UserAuthentication.Model.User;

public class UserMgmtController {
    private UserMgmtUI staffInterface;
    private CourseMgmtController courseMgmtCntrl;
    private Instructor instructor;
    private Admin admin;
    private TA ta;
    private int accessLevel;

    /**
     * Constructor for the staff management interface
     * @param u1 Staff user profile to be managed through interface
     */
    public UserMgmtController(User u1) {
        this.setStaffInterface(new UserMgmtUI());
        this.setAccessLevel(this.checkFacultyLevel(u1));
        this.getStaffInterface().displayProfile(u1,this.getAccessLevel());
        //this.setCourseMgmtCntrl(new CourseMgmtController());
    }

    /**
     * Assigns RoleLevel for restricting access to parts of interface
     * @param u1
     * @return
     */
    public int checkFacultyLevel(User u1){
        int roleLevel = 0;
        switch (u1.getRoleID()){
            case "TA":
                this.setTa(new TA(u1.getUserName(), u1.getUserName(), u1.getPassword(), u1.getRoleID()));
                roleLevel = 1;
                break;
            case "INSTRUCTOR":
                this.setInstructor(new Instructor(u1.getUserIDNumber(),u1.getUserName(), u1.getFirstName(), u1.getLastName(), u1.getPassword(), u1.getRoleID()));
                roleLevel = 2;
                break;
            case "ADMIN":
                this.setAdmin(new Admin(u1.getUserName(), u1.getUserName(), u1.getPassword(), u1.getRoleID()));
                roleLevel = 3;
                break;
            default:
                break;
        }
        return roleLevel;
    }

    public UserMgmtUI getStaffInterface() {
        return staffInterface;
    }

    public void setStaffInterface(UserMgmtUI staffInterface) {
        this.staffInterface = staffInterface;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public TA getTa() {
        return ta;
    }

    public void setTa(TA ta) {
        this.ta = ta;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}
