package CourseManagement.Controller;

import CourseManagement.Model.*;
import CourseManagement.View.*;
import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;
import StaffManagement.Model.Instructor;
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
    private Course newCourse;
    private CourseList courseList;
    private Page newPage;
    private Lesson newLesson;
    private Announcement newAnnouncement;
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
        this.newCourse = new Course("","","");
        this.courseList = new CourseList();
        this.courseTable = new CourseTableModel(this.getCourseList().getCourses());
        this.courseMgmtUI = new CourseMgmtUI(this);
        this.pageList = new PageList();
        this.pageTable = new PageTableModel(this.getPageList().getPages());
        this.pageMgmtUI = new PageMgmtUI(this);
        verifyButtonAccess();
    }

    private void verifyButtonAccess() {
        if (homepageController.getUser().getLoginID().equalsIgnoreCase("Student") || homepageController.getUser().getLoginID().equalsIgnoreCase("TA"))
        {
            courseMgmtUI.getAddCourseButton().setVisible(false);
            courseMgmtUI.getDeleteCourseButton().setVisible(false);
            courseMgmtUI.getEditCourseButton().setVisible(false);
            if (pageMgmtUI != null)
            {
                pageMgmtUI.getAddPageButton().setVisible(false);
                pageMgmtUI.getDeletePageButton().setVisible(false);
                pageMgmtUI.getEditPageButton().setVisible(false);
            }
        }
        else if (homepageController.getUser().getLoginID().equalsIgnoreCase("Instructor"))
        {
            courseMgmtUI.getAddCourseButton().setVisible(false);
            courseMgmtUI.getDeleteCourseButton().setVisible(false);
        }
    }

    private Instructor testInstructor() {
        String testLogin = "Instructor1";
        String testPassword = "Password1";
        String testRoleID = "ID1";

        Instructor testInst = new Instructor(testLogin, testPassword, testRoleID);

        return testInst;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        /**
         *Course list buttons
         */
        //View Course Control
        if (obj == this.getCourseMgmtUI().getViewCourseButton())
        {
            if (this.getPageMgmtUI() != null)
            {
                this.setPageMgmtUI(new PageMgmtUI(this));
                this.pageMgmtUI.addALPageButtons();
                verifyButtonAccess();
                this.homepageController.getHomepageUI().getViewPanel().add(this.pageMgmtUI.getPageMgmtPanel(), "View Course");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Course");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }

        //Add Course Handoff
        if (obj == this.courseMgmtUI.getAddCourseButton())
        {
            this.addCourseUI = new AddCourseUI(this);
            this.addCourseUI.addALNewCourseButtons();
            this.homepageController.getHomepageUI().getHomeFrame().setEnabled(false);
        }

        // Add Course Buttons
        if (this.addCourseUI != null)
        {
            if (obj == this.addCourseUI.getCancelButton())
                {
                    this.homepageController.getHomepageUI().getHomeFrame().setEnabled(true);
                    this.addCourseUI.getAddCourseFrame().dispose();
                    this.addCourseUI = null;
                }
            else if (obj == this.addCourseUI.getSaveButton())
            {
                newCourse.setCourseID(addCourseUI.getCourseIDTextField().getText());
                newCourse.setCourseName(addCourseUI.getCourseNameTextField().getText());
                newCourse.setMaxEnrolled(addCourseUI.getMaxEnrolledTextField().getText());
                /**
                 * Instructor for test purposes only
                 */
                newCourse.setInstructor(testInstructor());

                this.courseList.getCourses().add(newCourse);
                this.homepageController.getHomepageUI().getHomeFrame().setEnabled(true);
                this.getAddCourseUI().getAddCourseFrame().dispose();
            }
        }

        /**
         * Page List Buttons
         */
        // View Page Control
        if (obj == this.getPageMgmtUI().getViewPageButton())
        {
            if(this.getViewPageUI() != null)
            {
                this.setViewPageUI(new ViewPageUI());
                this.homepageController.getHomepageUI().getViewPanel().add(this.viewPageUI.getPagePanel(), "View Page");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Page");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }
        // Add Page Handoff
        if (obj == this.getPageMgmtUI().getAddPageButton())
        {
            this.addPageUI = new AddPageUI(this);
            this.addPageUI.addALNewPageButtons();
            this.homepageController.getHomepageUI().getHomeFrame().setEnabled(false);
        }

        // Add Page Buttons
        if (this.addPageUI != null)
        {
            if (obj == this.addPageUI.getCancelButton())
            {
                this.homepageController.getHomepageUI().getHomeFrame().setEnabled(true);
                this.addPageUI.getAddPageFrame().dispose();
                this.addPageUI = null;
            }
            else if (obj == this.getAddPageUI().getSelectButton())
            {
                // Add New Announcement
                if (this.getAddPageUI().getAnnouncementRadioButton().isSelected())
                {
                    this.addPageUI.getAnnouncementRadioButton().setSelected(true);
                    this.addAnnouncementUI = new AddAnnouncementUI(this);
                    this.addAnnouncementUI.addALNewAnnouncementButtons();
                    this.addPageUI.getAddPageFrame().dispose();
                    this.homepageController.getHomepageUI().getHomeFrame().setEnabled(false);

                    if (obj == this.getAddAnnouncementUI().getCancelButton())
                    {
                        this.homepageController.getHomepageUI().getHomeFrame().setEnabled(true);
                        this.addAnnouncementUI.getAddAnnouncementFrame().dispose();
                        this.addAnnouncementUI = null;
                    }

                    else if (obj == this.addAnnouncementUI.getSaveButton())
                    {
                        newAnnouncement.setPageTitle(addAnnouncementUI.getTitleTextField().getText());
                        newAnnouncement.setAnnouncementBody(addAnnouncementUI.getAnnouncementTextArea().getText());

                        this.pageList.getPages().add(newAnnouncement);
                        this.homepageController.getHomepageUI().getHomeFrame().setEnabled(true);
                        this.getAddCourseUI().getAddCourseFrame().dispose();
                    }
                }

                // Add New Lesson Module Page
                if (this.getAddPageUI().getLessonModuleRadioButton().isSelected())
                {
                    this.addPageUI.getLessonModuleRadioButton().setSelected(true);
                    this.addLessonUI = new AddLessonUI(this);
                    this.addLessonUI.addALNewLessonButtons();
                    this.addPageUI.getAddPageFrame().dispose();
                    this.homepageController.getHomepageUI().getHomeFrame().setEnabled(false);

                    if (obj == this.addLessonUI.getCancelButton())
                    {
                        this.homepageController.getHomepageUI().getHomeFrame().setEnabled(true);
                        this.addLessonUI.getAddLessonFrame().dispose();
                        this.addLessonUI = null;
                    }

                    else if (obj == this.addLessonUI.getSaveButton())
                    {
                        newLesson.setPageTitle(addLessonUI.getTitleTextField().getText());
                        newLesson.setLessonContent(addLessonUI.getContentTextArea().getText());
                        newLesson.setAssignedReading(addLessonUI.getReadingTextArea().getText());

                        this.pageList.getPages().add(newLesson);
                        this.homepageController.getHomepageUI().getHomeFrame().setEnabled(true);
                        this.getAddCourseUI().getAddCourseFrame().dispose();
                    }
                }

                // Add New Assignment
                else if (this.getAddPageUI().getAssignmentRadioButton().isSelected())
                {
                    if(this.getCourseworkMgmtCntrl() == null) {
                    }
                }
            }
        }
    }

    /**
     * Students will have different access to Courses than staff, so they
     * will utilize a different constructor
     */
    public CourseMgmtController(StudentMgmtController studentMgmtController) {
        String successful = "\n---Course Information Displayed Correctly---";
        this.setStudentMgmtController(studentMgmtController);

        // Creating an example course w/ pages/assignments/questions for student to access during integration test
        Course studentsCourse = new Course("IST 412", "Complicated Stuff", "15");
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
        this.setNewCourse(studentsCourse);
        this.setNewPage(studentsCourse.getCoursePages().get(0));

        // initiates the courseworkMgmtController made for students
        this.setCourseworkMgmtCntrl(new CourseworkMgmtController(this, this.getStudentMgmtController().getStudent()));
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

    public Course getNewCourse() {
        return newCourse;
    }

    public void setNewCourse(Course newCourse) {
        this.newCourse = newCourse;
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

    public Page getNewPage() {
        return newPage;
    }

    public void setNewPage(Page newPage) {
        this.newPage = newPage;
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

    public Lesson getNewLesson() {
        return newLesson;
    }

    public void setNewLesson(Lesson newLesson) {
        this.newLesson = newLesson;
    }

    public Announcement getNewAnnouncement() {
        return newAnnouncement;
    }

    public void setNewAnnouncement(Announcement newAnnouncement) {
        this.newAnnouncement = newAnnouncement;
    }
}
