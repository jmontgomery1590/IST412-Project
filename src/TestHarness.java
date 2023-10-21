import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import CourseManagement.View.CourseMgmtInterface;
import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.AssignmentList;
import StaffManagement.Model.Instructor;
import UserAuthentication.Controller.LoginController;
import UserAuthentication.Model.User;
import UserAuthentication.View.HomepageUI;
import UserAuthentication.View.LoginInterface;

import java.util.ArrayList;

public class TestHarness {
    public static void main(String[] args) {
        //LoginController loginCTRL = new LoginController();
    	//HomepageUI homeUI = new HomepageUI();
        testCourseworkUseCase();
        //CourseMgmtInterface courseUI = new CourseMgmtInterface();

        //CourseMgmtController courseCntl = new CourseMgmtController();
    }

    public static void testCourseworkUseCase(){
        User testAdmin = new User("Admin", "Admin123");
        AssignmentList testCourseList = new AssignmentList();
        testCourseList.createAssignmentsList();
        CourseworkMgmtController testController = new CourseworkMgmtController(testAdmin);
    }
}
