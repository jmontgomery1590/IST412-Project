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
    private PageMgmtUI pageMgmtUI;
    private CourseworkMgmtController courseworkMgmtCntrl;
    private StudentMgmtController studentMgmtController;
    private Course newCourse;
    private Course selectedCourse;
    private CourseList courseList;
    private Page newPage;
    private Lesson newLesson;
    private Announcement newAnnouncement;
    private HomepageController homepageController;
    private CourseTableModel courseTable;
    private PageTableModel pageTable;
    private LessonTableModel lessonTable;
    private AnnouncementTableModel announcementTable;
    private PageList pageList;
    private LessonList lessonList;
    private AnnouncementList announcementList;
    private AddLessonUI addLessonUI;
    private AddAnnouncementUI addAnnouncementUI;
    private LessonMgmtUI lessonMgmtUI;
    private AnnouncementMgmtUI announcementMgmtUI;


    /**
     * Constructor for Course Management Interface
     */
    public CourseMgmtController(HomepageController homepageController) {
        this.homepageController = homepageController;
        newCourse = new Course("", "", "");
        this.courseList = new CourseList();
        this.courseTable = new CourseTableModel(this.getCourseList().getCourses());
        this.courseMgmtUI = new CourseMgmtUI(this);
        this.pageList = new PageList();
        this.pageTable = new PageTableModel(this.getPageList().getPages());
        this.pageMgmtUI = new PageMgmtUI(this);
        this.announcementList = new AnnouncementList();
        this.announcementTable = new AnnouncementTableModel(this.getAnnouncementList().getAnnouncements());
        this.announcementMgmtUI = new AnnouncementMgmtUI(this);
        verifyButtonAccess();
    }

    private void verifyButtonAccess() {
        if (homepageController.getUser().getLoginID().equalsIgnoreCase("Student") || homepageController.getUser().getLoginID().equalsIgnoreCase("TA"))
        {
            courseMgmtUI.getAddCourseButton().setVisible(false);
            courseMgmtUI.getDeleteCourseButton().setVisible(false);
            courseMgmtUI.getEditCourseButton().setVisible(false);
            if (lessonMgmtUI != null)
            {
                lessonMgmtUI.getAddLessonButton().setVisible(false);
                lessonMgmtUI.getDeleteLessonButton().setVisible(false);
                lessonMgmtUI.getEditLessonButton().setVisible(false);
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
                int selectedRow = this.courseMgmtUI.getCourseTable().getSelectedRow();
                selectedCourse = this.courseList.getCourses().get(selectedRow);
                this.setPageMgmtUI(new PageMgmtUI(this));
                this.pageMgmtUI.addALPageButtons();
                verifyButtonAccess();
                this.homepageController.getHomepageUI().getViewPanel().add(this.pageMgmtUI.getPageMgmtPanel(), "View Course");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Course");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }

        if (obj == this.getPageMgmtUI().getAnnouncementsButton())
        {
            if (this.getAnnouncementMgmtUI() == null)
            {
                this.setAnnouncementMgmtUI(new AnnouncementMgmtUI(this));
                this.announcementMgmtUI.addALAnnouncementButtons();
                this.homepageController.getHomepageUI().getViewPanel().add(this.announcementMgmtUI.getAnnouncementMgmtPanel(), "View Announcements");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Course");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }

        if (obj == this.getPageMgmtUI().getLessonsButton())
        {
            if (this.getLessonMgmtUI() == null)
            {
                this.setLessonMgmtUI(new LessonMgmtUI(this));
                this.lessonMgmtUI.addALLessonButtons();
                verifyButtonAccess();
                this.homepageController.getHomepageUI().getViewPanel().add(this.lessonMgmtUI.getLessonMgmtPanel(), "View Lessons");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Course");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }

        /*if (obj == this.getPageMgmtUI().getAssignmentsButton()) {
            if (this.getCourseworkMgmtCntrl().getCourseworkMgmtInterface() == null)
            {

            }
        }*/

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

    public AnnouncementList getAnnouncementList() {
        return announcementList;
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

    public AddCourseUI getAddCourseUI() {
        return addCourseUI;
    }

    public Page getNewPage() {
        return newPage;
    }

    public void setNewPage(Page newPage) {
        this.newPage = newPage;
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

    public CourseTableModel getCourseTable() {
        return courseTable;
    }

    public LessonMgmtUI getLessonMgmtUI() {
        return lessonMgmtUI;
    }

    public void setLessonMgmtUI(LessonMgmtUI lessonMgmtUI) {
        this.lessonMgmtUI = lessonMgmtUI;
    }

    public AnnouncementMgmtUI getAnnouncementMgmtUI() {
        return announcementMgmtUI;
    }

    public void setAnnouncementMgmtUI(AnnouncementMgmtUI announcementMgmtUI) {
        this.announcementMgmtUI = announcementMgmtUI;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
}
