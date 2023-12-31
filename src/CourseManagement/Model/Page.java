package CourseManagement.Model;

import CourseworkManagement.Model.Assignment;

import java.util.ArrayList;

public class Page {
    private String pageTitle;

    /**
     * Constructor for the Page class
     * @param title Title for the page
     */
    public Page (String title) {
        this.setPageTitle(title);
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
