package CourseManagement.Model;

public class Activity extends Page{
    public String activityType;
    public String questions;
    public String answers;


    /**
     * Constructor for the Page class
     *
     * @param title Title for the page
     */
    public Activity(String title) {
        super(title);
    }

    public Activity(String title, String type, String question, String answer) {
        super(title);
        this.activityType = type;
        this.questions = question;
        this.answers = answer;
    }
}
