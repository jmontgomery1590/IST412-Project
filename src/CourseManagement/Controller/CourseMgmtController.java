package CourseManagement.Controller;

import CourseManagement.Model.*;
import CourseManagement.View.CourseMgmtInterface;
import CourseManagement.View.ViewCourseUI;
import CourseManagement.View.ViewPageUI;
import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;
import StudentManagement.StudentMgmtController;
import UserAuthentication.Controller.HomepageController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseMgmtController implements ActionListener {
    private CourseMgmtInterface ci;
    private ViewCourseUI pi;
    private CourseworkMgmtController courseworkMgmtCntrl;
    private StudentMgmtController studentMgmtController;
    private Course currentCourse;
    private CourseList courseList;
    private Page currentPage;
    private HomepageController homepageController;
    private CourseTableModel courseTable;
    private PageTableModel pageTable;
    private PageList pageList;
    private ViewPageUI pageUI;


    /**
     * Constructor for Course Management Interface
     */
    public CourseMgmtController(HomepageController homepageController) {
        this.homepageController = homepageController;
        this.courseList = new CourseList();
        this.courseTable = new CourseTableModel(this.courseList.getCourses());
        this.setCi(new CourseMgmtInterface(this));
        this.pageList = new PageList();
        this.pageTable = new PageTableModel(this.pageList.getPages());
        addALCourseButtons();
        verifyButtonAccess();
    }

    private void verifyButtonAccess() {
        if (homepageController.getUser().getLoginID().equalsIgnoreCase("Student") || homepageController.getUser().getLoginID().equalsIgnoreCase("TA")) {
            ci.getAddCourseButton().setVisible(false);
            ci.getDeleteCourseButton().setVisible(false);
            ci.getEditCourseButton().setVisible(false);
            if (pi != null) {
                pi.getAddPageButton().setVisible(false);
                pi.getDeletePageButton().setVisible(false);
                pi.getEditPageButton().setVisible(false);
            }
        } else if (homepageController.getUser().getLoginID().equalsIgnoreCase("Instructor")) {
            ci.getAddCourseButton().setVisible(false);
            ci.getDeleteCourseButton().setVisible(false);
        }
    }

    public void addALCourseButtons() {
        this.getCi().getEditCourseButton().addActionListener(this);
        this.getCi().getAddCourseButton().addActionListener(this);
        this.getCi().getViewCourseButton().addActionListener(this);
        this.getCi().getDeleteCourseButton().addActionListener(this);
    }

    public void addALPageButtons() {
        this.getPi().getViewPageButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == this.getCi().getViewCourseButton()) {
            if (this.getPi() == null) {
                this.setPi(new ViewCourseUI(this));
                this.addALPageButtons();
                verifyButtonAccess();
                this.homepageController.getHomepageUI().getViewPanel().add(this.pi.getPageMgmtPanel(), "View Course");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Course");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }

        else if (obj == this.getPi().getViewPageButton()) {
            if(this.getPageUI() == null) {
                this.setPageUI(new ViewPageUI());
                this.homepageController.getHomepageUI().getViewPanel().add(this.pageUI.getPagePanel(), "View Page");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Page");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }
    }

    /**
     * Students will have different access to Courses than staff, so they
     * will utilize a different constructor
     */
    public CourseMgmtController(StudentMgmtController studentMgmtController){
        String successful = "\n---Course Information Displayed Correctly---";
        this.setStudentMgmtController(studentMgmtController);

        // Creating an example course w/ pages/assignments/questions for student to access during integration test
        Course studentsCourse = new Course("IST 412", "Complicated Stuff", 15);
        Page studentsCoursePage = new Page("L01 Lesson Description");
        Assignment studentsAssignment = new Assignment("L01: Your Assignment");
        Question studentsQuestion = new Question("How many points is this question worth?", 9001);
        Answer studentAnswer = new Answer("It's over 9000", true);
        Answer studentAnswer2 = new Answer("It's not over 9000", false);
        studentsQuestion.addPossibleAnswer(studentAnswer);
        studentsQuestion.addPossibleAnswer(studentAnswer2);
        studentsAssignment.getAssignmentQuestions().add(studentsQuestion);
        studentsCourse.getCoursePages().add(studentsCoursePage);
        this.getStudentMgmtController().getStudent().getEnrolledCourses().add(studentsCourse);
        this.setCurrentCourse(studentsCourse);
        this.setCurrentPage(studentsCourse.getCoursePages().get(0));

        // initiates the courseworkMgmtController made for students
        this.setCourseworkMgmtCntrl(new CourseworkMgmtController(this, this.getStudentMgmtController().getStudent()));
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
            System.out.println("Page Content: ");
        }
    }

    public PageList getPageList() {
        return pageList;
    }

    public void setPageList(PageList pageList) {
        this.pageList = pageList;
    }

    public ViewPageUI getPageUI() {
        return pageUI;
    }

    public void setPageUI(ViewPageUI pageUI) {
        this.pageUI = pageUI;
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

    public ViewCourseUI getPi() {
        return pi;
    }

    public void setPi(ViewCourseUI pi) {
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

    public PageTableModel getPageTable() {
        return pageTable;
    }

    public void setPageTable(PageTableModel pageTable) {
        this.pageTable = pageTable;
    }
}
