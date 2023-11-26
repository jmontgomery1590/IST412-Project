package CourseManagement.Model;

public class Lesson extends Page{
    private String lessonContent;
    private String assignedReading;
    /**
     * Constructor for the Page class
     * @param title Title for the page
     */
    public Lesson(String title) {
        super(title);
    }

    public Lesson(String title, String content, String reading) {
        super (title);
        this.lessonContent = content;
        this.assignedReading = reading;
    }

    public String getLessonContent() {
        return lessonContent;
    }

    public void setLessonContent(String lessonContent) {
        this.lessonContent = lessonContent;
    }

    public String getAssignedReading() {
        return assignedReading;
    }

    public void setAssignedReading(String assignedReading) {
        this.assignedReading = assignedReading;
    }
}
