package CourseManagement.Controller;

import CourseManagement.Model.Course;
import CourseManagement.Model.Page;
import CourseManagement.View.CourseMgmtInterface;
import CourseManagement.View.PageMgmtInterface;
import CourseworkManagement.Controller.CourseworkMgmtController;

public class CourseMgmtController {
    private CourseMgmtInterface ci;
    private PageMgmtInterface pi;
    private CourseworkMgmtController courseworkMgmtCntrl;
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
}
