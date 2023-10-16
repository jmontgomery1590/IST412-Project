package UserAuthentication.Controller;

import StaffManagement.Controller.StaffMgmtController;
import StudentManagement.StudentMgmtController;
import UserAuthentication.View.LoginInterface;
import UserAuthentication.Model.User;

// test update
public class LoginController {
    private User u1;
    private LoginInterface loginInt;
    private StudentMgmtController studentCntrl;
    private StaffMgmtController staffMgmtCntrl;


    public LoginController() {
        this.setLoginInt(new LoginInterface());
        this.setU1(this.getLoginInt().Login());
        this.getU1().setRoleID(this.getU1().verifyUser());

        switch (this.getU1().getRoleID()){
            case "STUDENT":
                this.studentCntrl = new StudentMgmtController(this.getU1());
                break;
            case "INSTRUCTOR", "ADMIN", "TA":
                this.staffMgmtCntrl = new StaffMgmtController(this.getU1());
                break;
            default:
                System.out.println(this.getU1().getRoleID());
                break;
        }
    }

    public LoginInterface getLoginInt() {
        return loginInt;
    }

    public void setLoginInt(LoginInterface loginInt) {
        this.loginInt = loginInt;
    }

    public User getU1() {
        return u1;
    }

    public void setU1(User u1) {
        this.u1 = u1;
    }
}
