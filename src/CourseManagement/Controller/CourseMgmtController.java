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
    private LessonList lessonList;
    private PageList pageList;
    private AnnouncementList announcementList;
    private CourseTableModel courseTable;
    private PageTableModel pageTable;
    private LessonTableModel lessonTable;
    private AnnouncementTableModel announcementTable;
    private ViewAnnouncementUI viewAnnouncementUI;
    private ViewLessonUI viewLessonUI;


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
        verifyButtonAccess();
    }

    private void verifyButtonAccess() {
        if (homepageController.getUser().getLoginID().equalsIgnoreCase("Student") || homepageController.getUser().getLoginID().equalsIgnoreCase("TA"))
        {
            courseMgmtUI.getAddCourseButton().setVisible(false);
            courseMgmtUI.getDeleteCourseButton().setVisible(false);
            courseMgmtUI.getEditCourseButton().setVisible(false);
        }
        else if (homepageController.getUser().getLoginID().equalsIgnoreCase("Instructor"))
        {
            courseMgmtUI.getAddCourseButton().setVisible(false);
            courseMgmtUI.getDeleteCourseButton().setVisible(false);
        }
    }

    /**
     * Students will have different access to Courses than staff, so they
     * will utilize a different constructor
     */

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
}
