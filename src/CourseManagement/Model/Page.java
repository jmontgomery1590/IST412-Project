package CourseManagement.Model;

import CourseworkManagement.Model.Assignment;

import java.util.ArrayList;

public class Page {
    private String pageTitle;
    private String pageBody;

    private ArrayList<Assignment> lessonAssignments;

    /**
     * Constructor for the Page class
     * @param title Title for the page
     * @param body Content for the page
     */
    public Page (String title, String body) {
        this.setPageTitle(title);
        this.setPageBody(body);
        this.setLessonAssignments(new ArrayList<>());
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageBody() {
        return pageBody;
    }

    public void setPageBody(String pageBody) {
        this.pageBody = pageBody;
    }

    public ArrayList<Assignment> getLessonAssignments() {
        return lessonAssignments;
    }

    public void setLessonAssignments(ArrayList<Assignment> lessonAssignments) {
        this.lessonAssignments = lessonAssignments;
    }
}
