package CourseManagement.Model;

public class Announcement extends Page{
    private String announcementBody;

    /**
     * Constructor for the Page class
     *
     * @param title Title for the page
     */
    public Announcement(String title) {
        super(title);
    }

    public Announcement(String title, String body) {
        super (title);
        this.announcementBody = body;
    }
}
