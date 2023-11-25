package CourseManagement.Controller;

import CourseManagement.Model.*;
import CourseManagement.View.*;
import CourseworkManagement.Controller.CourseworkMgmtController;
import DatabaseMgmt.DatabaseConnection;
import UserAuthentication.Controller.HomepageController;
import UserAuthentication.Model.User;

import javax.xml.crypto.Data;

public class CourseMgmtController {
    private CourseMgmtUI courseMgmtUI;
    private PageMgmtUI pageMgmtUI;
    private LessonMgmtUI lessonMgmtUI;
    private AnnouncementMgmtUI announcementMgmtUI;
    private AddCourseUI addCourseUI;
    private AddLessonUI addLessonUI;
    private AddAnnouncementUI addAnnouncementUI;
    private CourseworkMgmtController courseworkMgmtCntrl;
    private HomepageController homepageController;
    private Course newCourse, selectedCourse;
    private CourseList courseList;
    private LessonList lessonList = new LessonList();
    private AnnouncementList announcementList;
    private CourseTableModel courseTable;
    private LessonTableModel lessonTable;
    private AnnouncementTableModel announcementTable;
    private ViewAnnouncementUI viewAnnouncementUI;
    private ViewLessonUI viewLessonUI;
    private Announcement announcement;
    private Lesson lesson;
    DatabaseConnection database;


    /**
     * Constructor for Course Management Interface
     */
    public CourseMgmtController(HomepageController homepageController) {
        this.homepageController = homepageController;
        //newCourse = new Course("", "", "",);
        this.courseList = new CourseList();
        loadCourseList();
        this.courseTable = new CourseTableModel(this.getCourseList().getCourses());
        this.courseMgmtUI = new CourseMgmtUI(this);
        verifyButtonAccess();
    }

    private void verifyButtonAccess() {
        if (homepageController.getUser().getRoleID().equals("3") || homepageController.getUser().getRoleID().equals("4"))
        {
            courseMgmtUI.getAddCourseButton().setVisible(false);
            courseMgmtUI.getDeleteCourseButton().setVisible(false);
            courseMgmtUI.getEditCourseButton().setVisible(false);
        }
        else if (homepageController.getUser().getRoleID().equals("2"))
        {
            courseMgmtUI.getAddCourseButton().setVisible(false);
            courseMgmtUI.getDeleteCourseButton().setVisible(false);
        }
    }

    private void loadCourseList() {
        User user = homepageController.getUser();
        database = new DatabaseConnection();
        if (user.getRoleID().equals("2"))
        {
            database.getInstructorCourseList(this);
        }
        else if (user.getRoleID().equals("4"))
        {
            database.getStudentCourseList(this);
        }
        else if (user.getRoleID().equals("1") || user.getRoleID().equals("5"))
        {
            database.getAdminCourseList(this);
        }
    }

    public void loadLessonList() {
        Course course = selectedCourse;
        database = new DatabaseConnection();
        database.getCourseLessonList(this);
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

    public PageMgmtUI getPageMgmtUI() {
        return pageMgmtUI;
    }

    public void setPageMgmtUI(PageMgmtUI pageMgmtUI) {
        this.pageMgmtUI = pageMgmtUI;
    }

    public void setCourseworkMgmtCntrl(CourseworkMgmtController courseworkMgmtCntrl) {
        this.courseworkMgmtCntrl = courseworkMgmtCntrl;
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

    public HomepageController getHomepageController() {
        return homepageController;
    }

    public CourseworkMgmtController getCourseworkMgmtCntrl() {
        return courseworkMgmtCntrl;
    }

    public void setAddCourseUI(AddCourseUI addCourseUI) {
        this.addCourseUI = addCourseUI;
    }

    public LessonTableModel getLessonTable() {
        return lessonTable;
    }

    public void setLessonTable(LessonTableModel lessonTable) {
        this.lessonTable = lessonTable;
    }

    public AnnouncementTableModel getAnnouncementTable() {
        return announcementTable;
    }

    public void setAnnouncementTable(AnnouncementTableModel announcementTable) {
        this.announcementTable = announcementTable;
    }

    public LessonList getLessonList() {
        return lessonList;
    }

    public void setLessonList(LessonList lessonList) {
        this.lessonList = lessonList;
    }

    public void setAnnouncementList(AnnouncementList announcementList) {
        this.announcementList = announcementList;
    }

    public void setAddLessonUI(AddLessonUI addLessonUI) {
        this.addLessonUI = addLessonUI;
    }

    public void setAddAnnouncementUI(AddAnnouncementUI addAnnouncementUI) {
        this.addAnnouncementUI = addAnnouncementUI;
    }

    public ViewAnnouncementUI getViewAnnouncementUI() {
        return viewAnnouncementUI;
    }

    public void setViewAnnouncementUI(ViewAnnouncementUI viewAnnouncementUI) {
        this.viewAnnouncementUI = viewAnnouncementUI;
    }

    public ViewLessonUI getViewLessonUI() {
        return viewLessonUI;
    }

    public void setViewLessonUI(ViewLessonUI viewLessonUI) {
        this.viewLessonUI = viewLessonUI;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
