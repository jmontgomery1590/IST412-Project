package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.Model.CourseList;
import CourseManagement.Model.CourseTableModel;
import CourseManagement.Model.Page;
import CourseManagement.View.CourseMgmtInterface;
import CourseManagement.View.PageMgmtInterface;
import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;
import StudentManagement.StudentMgmtController;
import UserAuthentication.Controller.HomepageController;

public class CourseMgmtController {
    private CourseMgmtInterface ci;
    private PageMgmtInterface pi;
    private CourseworkMgmtController courseworkMgmtCntrl;
    private StudentMgmtController studentMgmtController;
    private Course currentCourse;
    private CourseList courseList;
    private Page currentPage;
    private HomepageController homepageController;
    private CourseTableModel courseTable;


    /**
     * Constructor for Course Management Interface
     */
    public CourseMgmtController(HomepageController homepageController) {
        this.homepageController = homepageController;
        this.courseList = new CourseList();
        this.courseTable = new CourseTableModel(this.courseList.getCourses());
        this.setCi(new CourseMgmtInterface(this));
        verifyButtonAccess();
    }

    private void verifyButtonAccess(){
        if (homepageController.getUser().getLoginID().equalsIgnoreCase("Student") || (homepageController.getUser().getLoginID().equalsIgnoreCase("TA")))
        {
            ci.getAddCourseButton().setVisible(false);
            ci.getDeleteCourseButton().setVisible(false);
            ci.getEditCourseButton().setVisible(false);
        } else if (homepageController.getUser().getLoginID().equalsIgnoreCase("Instructor")){
            ci.getAddCourseButton().setVisible(false);
            ci.getDeleteCourseButton().setVisible(false);
        }
    }

    public void createNewCoursePage(){
        this.setPi(new PageMgmtInterface());
    }

    public void printCourseInfo() {
        System.out.println("Course ID: " + this.getCurrentCourse().getCourseID());
        System.out.println("Course Name: " + this.getCurrentCourse().getCourseName());
        System.out.println("Max Enrolled Students: " + this.getCurrentCourse().getMaxEnrolled());
    }

    public void printCoursePages() {
        for (Page pages: this.getCurrentCourse().getCoursePages())
        {
            System.out.println("Page Title: " + pages.getPageTitle());
            System.out.println("Page Content: " + pages.getPageBody() + "\n");
        }
    }

    public CourseMgmtInterface getCi() {
        return ci;
    }

    public void setCi(CourseMgmtInterface ci) {
        this.ci = ci;
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

    public PageMgmtInterface getPi() {
        return pi;
    }

    public void setPi(PageMgmtInterface pi) {
        this.pi = pi;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public CourseworkMgmtController getCourseworkMgmtCntrl() {
        return courseworkMgmtCntrl;
    }

    public void setCourseworkMgmtCntrl(CourseworkMgmtController courseworkMgmtCntrl) {
        this.courseworkMgmtCntrl = courseworkMgmtCntrl;
    }

    public StudentMgmtController getStudentMgmtController() {
        return studentMgmtController;
    }

    public void setStudentMgmtController(StudentMgmtController studentMgmtController) {
        this.studentMgmtController = studentMgmtController;
    }

    public CourseList getCourseList() {
        return courseList;
    }

    public void setCourseList(CourseList courseList) {
        this.courseList = courseList;
    }

    public HomepageController getHomepageController() {
        return homepageController;
    }

    public void setHomepageController(HomepageController homepageController) {
        this.homepageController = homepageController;
    }

    public CourseTableModel getCourseTable() {
        return courseTable;
    }

    public void setCourseTable(CourseTableModel courseTable) {
        this.courseTable = courseTable;
    }
}
