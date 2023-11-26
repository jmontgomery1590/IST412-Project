package CourseManagement.Model;

public class Announcement extends Page{
    private String announcementContent;

    /**
     * Constructor for the Page class
     * @param title Title for the page
     */
    public Announcement(String title) {
        super(title);
    }

    public Announcement(String title, String content) {
        super (title);
        this.announcementContent = content;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }
}
