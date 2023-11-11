package CourseManagement.Controller;

import CourseManagement.Model.*;
import CourseManagement.View.*;
import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;
import StudentManagement.StudentMgmtController;
import UserAuthentication.Controller.HomepageController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseMgmtController implements ActionListener {
    private CourseMgmtUI courseMgmtUI;
    private AddCourseUI addCourseUI;
    private AddPageUI addPageUI;
    private PageMgmtUI pageMgmtUI;
    private CourseworkMgmtController courseworkMgmtCntrl;
    private StudentMgmtController studentMgmtController;
    private Course currentCourse;
    private CourseList courseList;
    private Page currentPage;
    private HomepageController homepageController;
    private CourseTableModel courseTable;
    private PageTableModel pageTable;
    private PageList pageList;
    private ViewPageUI viewPageUI;
    private AddLessonUI addLessonUI;
    private AddAnnouncementUI addAnnouncementUI;


    /**
     * Constructor for Course Management Interface
     */
    public CourseMgmtController(HomepageController homepageController) {
        this.homepageController = homepageController;
        this.courseList = new CourseList();
        this.courseTable = new CourseTableModel(this.courseList.getCourses());
        this.setCourseMgmtUI(new CourseMgmtUI(this));
        this.pageList = new PageList();
        this.pageTable = new PageTableModel(this.pageList.getPages());
        addALCourseButtons();
        verifyButtonAccess();
    }

    private void verifyButtonAccess() {
        if (homepageController.getUser().getLoginID().equalsIgnoreCase("Student") || homepageController.getUser().getLoginID().equalsIgnoreCase("TA")) {
            courseMgmtUI.getAddCourseButton().setVisible(false);
            courseMgmtUI.getDeleteCourseButton().setVisible(false);
            courseMgmtUI.getEditCourseButton().setVisible(false);
            if (pageMgmtUI != null) {
                pageMgmtUI.getAddPageButton().setVisible(false);
                pageMgmtUI.getDeletePageButton().setVisible(false);
                pageMgmtUI.getEditPageButton().setVisible(false);
            }
        } else if (homepageController.getUser().getLoginID().equalsIgnoreCase("Instructor")) {
            courseMgmtUI.getAddCourseButton().setVisible(false);
            courseMgmtUI.getDeleteCourseButton().setVisible(false);
        }
    }

    public void addALCourseButtons() {
        this.getCourseMgmtUI().getViewCourseButton().addActionListener(this);
        this.getCourseMgmtUI().getAddCourseButton().addActionListener(this);
        this.getCourseMgmtUI().getEditCourseButton().addActionListener(this);
        this.getCourseMgmtUI().getDeleteCourseButton().addActionListener(this);
    }

    public void addALPageButtons() {
        this.getPageMgmtUI().getViewPageButton().addActionListener(this);
        this.getPageMgmtUI().getAddPageButton().addActionListener(this);
        this.getPageMgmtUI().getEditPageButton().addActionListener(this);
        this.getPageMgmtUI().getDeletePageButton().addActionListener(this);
    }

    public void addALNewCourseButtons() {
        this.getAddCourseUI().getSaveButton().addActionListener(this);
        this.getAddCourseUI().getCancelButton().addActionListener(this);
    }

    public void addALNewPageButtons() {
        this.getAddPageUI().getSelectButton().addActionListener(this);
        this.getAddPageUI().getCancelButton().addActionListener(this);
    }

    public void addALNewLessonButtons() {
        this.getAddLessonUI().getSaveButton().addActionListener(this);
        this.getAddLessonUI().getCancelButton().addActionListener(this);
    }

    public void addALNewAnnouncementButtons() {
        this.getAddAnnouncementUI().getSaveButton().addActionListener(this);
        this.getAddAnnouncementUI().getCancelButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        /**
         *Course list buttons
         */
        if (obj == this.getCourseMgmtUI().getViewCourseButton()) {
            if (this.getPageMgmtUI() == null) {
                this.setPageMgmtUI(new PageMgmtUI(this));
                this.addALPageButtons();
                verifyButtonAccess();
                this.homepageController.getHomepageUI().getViewPanel().add(this.pageMgmtUI.getPageMgmtPanel(), "View Course");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Course");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }

        else if (obj == this.getCourseMgmtUI().getAddCourseButton()) {
            if (this.getAddCourseUI() == null) {
                this.setAddCourseUI(new AddCourseUI(this));
                this.addALNewCourseButtons();
                //this.homepageController.getHomepageUI().getHomeFrame().setEnabled(false);
            }
        }

        /*else if (obj == this.getAddCourseUI().getSaveButton()) {

        }*/

        else if (obj == this.getAddCourseUI().getCancelButton()) {
            this.getCourseMgmtUI().getCourseFrame().setEnabled(true);
            this.getAddCourseUI().getAddCourseFrame().dispose();
        }

        /**
         * Page List view buttons
         */
        else if (obj == this.getPageMgmtUI().getViewPageButton()) {
            if(this.getViewPageUI() == null) {
                this.setViewPageUI(new ViewPageUI());
                this.homepageController.getHomepageUI().getViewPanel().add(this.viewPageUI.getPagePanel(), "View Page");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Page");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }

        else if (obj == this.getPageMgmtUI().getAddPageButton()) {
            if (this.getAddPageUI() == null) {
                this.setAddPageUI(new AddPageUI(this));
                this.addALNewPageButtons();
                //this.homepageController.getHomepageUI().getHomeFrame().setEnabled(false);
            }
        }

        else if (obj == this.getAddPageUI().getSelectButton()) {
            if (obj == this.getAddPageUI().getLessonModuleRadioButton()) {
                if(this.getAddLessonUI() == null) {
                    this.setAddLessonUI(new AddLessonUI(this));
                    this.addALNewLessonButtons();
                    this.homepageController.getHomepageUI().getHomeFrame().setEnabled(false);
                }
            }
            else if (obj == this.getAddPageUI().getAnnouncementRadioButton()) {
                if(this.getAddAnnouncementUI() == null) {
                    this.setAddAnnouncementUI(new AddAnnouncementUI(this));
                    this.addALNewAnnouncementButtons();
                    this.homepageController.getHomepageUI().getHomeFrame().setEnabled(false);
                }
            }
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

    public ViewPageUI getViewPageUI() {
        return viewPageUI;
    }

    public void setViewPageUI(ViewPageUI viewPageUI) {
        this.viewPageUI = viewPageUI;
    }

    public CourseMgmtUI getCourseMgmtUI() {
        return courseMgmtUI;
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

    public PageMgmtUI getPageMgmtUI() {
        return pageMgmtUI;
    }

    public void setPageMgmtUI(PageMgmtUI pageMgmtUI) {
        this.pageMgmtUI = pageMgmtUI;
    }

    public void setCourseMgmtUI(CourseMgmtUI courseMgmtUI) {
        this.courseMgmtUI = courseMgmtUI;
    }

    public AddCourseUI getAddCourseUI() {
        return addCourseUI;
    }

    public void setAddCourseUI(AddCourseUI addCourseUI) {
        this.addCourseUI = addCourseUI;
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

    public AddPageUI getAddPageUI() {
        return addPageUI;
    }

    public void setAddPageUI(AddPageUI addPageUI) {
        this.addPageUI = addPageUI;
    }

    public AddLessonUI getAddLessonUI() {
        return addLessonUI;
    }

    public void setAddLessonUI(AddLessonUI addLessonUI) {
        this.addLessonUI = addLessonUI;
    }

    public AddAnnouncementUI getAddAnnouncementUI() {
        return addAnnouncementUI;
    }

    public void setAddAnnouncementUI(AddAnnouncementUI addAnnouncementUI) {
        this.addAnnouncementUI = addAnnouncementUI;
    }
}
