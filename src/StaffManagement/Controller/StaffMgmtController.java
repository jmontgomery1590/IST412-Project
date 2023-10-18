package StaffManagement.Controller;

import CourseManagement.Controller.CourseMgmtController;
import StaffManagement.Model.Admin;
import StaffManagement.Model.Instructor;
import StaffManagement.Model.TA;
import StaffManagement.View.StaffMgmtInterface;
import UserAuthentication.Model.User;

public class StaffMgmtController {
    private StaffMgmtInterface staffInterface;
    private CourseMgmtController courseMgmtCntrl;
    private Instructor instructor;
    private Admin admin;
    private TA ta;
    private int accessLevel;

    /**
     * Constructor for the staff management interface
     * @param u1 Staff user profile to be managed through interface
     */
    public StaffMgmtController(User u1) {
        this.setStaffInterface(new StaffMgmtInterface());
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
                this.setTa(new TA(u1.getLoginID(), u1.getPassword(), u1.getRoleID()));
                roleLevel = 1;
                break;
            case "INSTRUCTOR":
                this.setInstructor(new Instructor(u1.getLoginID(), u1.getPassword(), u1.getRoleID()));
                roleLevel = 2;
                break;
            case "ADMIN":
                this.setAdmin(new Admin(u1.getLoginID(), u1.getPassword(), u1.getRoleID()));
                roleLevel = 3;
                break;
            default:
                break;
        }
        return roleLevel;
    }

    public StaffMgmtInterface getStaffInterface() {
        return staffInterface;
    }

    public void setStaffInterface(StaffMgmtInterface staffInterface) {
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
