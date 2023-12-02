package CourseManagement.Model;

public class Lesson extends Page{
    private String lessonContent;
    private String assignedReading;
    private int courseTableID;
    private int lessonID;

    /**
     * Constructor for the Page class
     * @param title Title for the page
     */
    public Lesson(String title) {
        super(title);
    }

    public Lesson(String title, String content, String reading) {
        super (title);
        lessonContent = content;
        assignedReading = reading;
    }

    public Lesson(int courseTableID, String title, String content, String reading) {
        super (title);
        this.courseTableID = courseTableID;
        lessonContent = content;
        assignedReading = reading;
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

    public int getCourseTableID() {
        return courseTableID;
    }

    public void setCourseTableID(int courseTableID) {
        this.courseTableID = courseTableID;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }
}
