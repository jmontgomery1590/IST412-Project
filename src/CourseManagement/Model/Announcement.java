package CourseManagement.Model;

public class Announcement extends Page{
    private String announcementContent;
    private int courseTableID;
    private int announcementID;

    /**
     * Constructor for the Page class
     * @param title Title for the page
     */
    public Announcement(String title) {
        super(title);
    }

    public Announcement(String title, String content) {
        super(title);
        announcementContent = content;
    }

    public Announcement(int courseTableID, String title, String content) {
        super (title);
        this.courseTableID = courseTableID;
        announcementContent = content;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public int getCourseTableID() {
        return courseTableID;
    }

    public void setCourseTableID(int courseTableID) {
        this.courseTableID = courseTableID;
    }

    public int getAnnouncementID() {
        return announcementID;
    }

    public void setAnnouncementID(int announcementID) {
        this.announcementID = announcementID;
    }
}

