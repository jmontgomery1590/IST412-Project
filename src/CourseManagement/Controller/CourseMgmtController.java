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
        Page studentsCoursePage = new Page("L01 Lesson Description", "This is your lesson.");
        Assignment studentsAssignment = new Assignment("L01: Your Assignment");
        Question studentsQuestion = new Question("How many points is this question worth?", 9001);
        Answer studentAnswer = new Answer("It's over 9000", true);
        Answer studentAnswer2 = new Answer("It's not over 9000", false);
        studentsQuestion.addPossibleAnswer(studentAnswer);
        studentsQuestion.addPossibleAnswer(studentAnswer2);
        studentsAssignment.getAssignmentQuestions().add(studentsQuestion);
        studentsCoursePage.getLessonAssignments().add(studentsAssignment);
        studentsCourse.getCoursePages().add(studentsCoursePage);
        this.getStudentMgmtController().getStudent().getEnrolledCourses().add(studentsCourse);
        this.setCurrentCourse(studentsCourse);
        this.setCurrentPage(studentsCourse.getCoursePages().get(0));

        // initiates the courseworkMgmtController made for students
        this.setCourseworkMgmtCntrl(new CourseworkMgmtController(this, this.getStudentMgmtController().getStudent()));
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
