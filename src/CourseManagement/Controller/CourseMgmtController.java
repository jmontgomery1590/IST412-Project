package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.Model.Page;
import CourseManagement.View.CourseMgmtInterface;
import CourseManagement.View.PageMgmtInterface;
import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;
import StudentManagement.Student;
import StudentManagement.StudentMgmtController;

public class CourseMgmtController {
    private CourseMgmtInterface ci;
    private PageMgmtInterface pi;
    private CourseworkMgmtController courseworkMgmtCntrl;
    private StudentMgmtController studentMgmtController;
    private Course currentCourse;
    private Page currentPage;


    /**
     * Constructor for Course Management Interface
     */
    public CourseMgmtController() {
        this.setCi(new CourseMgmtInterface());
        this.setCurrentCourse(this.getCi().createCourse());
        this.printCourseInfo();
        this.createNewCoursePage();

        // Add page to the current created course
        this.setCurrentPage(this.getPi().createPage());
        this.getCurrentCourse().getCoursePages().add(this.getCurrentPage());

        // Add more pages to the current created course for purpose of testing
        this.setCurrentPage(this.getPi().createPage());
        this.getCurrentCourse().getCoursePages().add(this.getCurrentPage());

        // Iterate through list of pages within the course
        this.printCoursePages();

        // Initiates CourseWork Management Controller passing in the current course
        this.setCourseworkMgmtCntrl(new CourseworkMgmtController(this));
    }

    /**
     * Students will have different access to Courses than staff, so they
     * will utilize a different constructor
     */
    public CourseMgmtController(StudentMgmtController studentMgmtController){
        this.setStudentMgmtController(studentMgmtController);

        // Creating a course w/ pages/assignments/questions for student to access as an example
        Course studentsCourse = new Course("IST 412", "Complicated Stuff", 15);
        Page studentsCoursePage = new Page("L01 Lesson Description", "This is your lesson.");
        Assignment studentsAssignment = new Assignment("L01: Your Assignment");
        Question studentsQuestion = new Question("L01 Question #1", 9001);
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

        // initiates the courseworkmgmtcontroller made for students
        this.setCourseworkMgmtCntrl(new CourseworkMgmtController(this, this.getStudentMgmtController().getStudent()));
    }

    public void createNewCoursePage(){
        this.setPi(new PageMgmtInterface());
    }

    public void printCourseInfo(){
        System.out.println(this.getCurrentCourse().getCourseID());
        System.out.println(this.getCurrentCourse().getCourseName());
        System.out.println(this.getCurrentCourse().getMaxEnrolled());
    }

    public void printCoursePages(){
        for (Page pages: this.getCurrentCourse().getCoursePages())
        {
            System.out.println(pages.getPageTitle());
            System.out.println("\n" + pages.getPageBody());
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
}
