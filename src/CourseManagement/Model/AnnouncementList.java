package CourseManagement.Model;

import java.util.ArrayList;

public class AnnouncementList {
    private Announcement announcement;
    private ArrayList<Announcement> announcements = new ArrayList<>();

    public AnnouncementList() {}

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public ArrayList<Announcement> getAnnouncements() {
        return announcements;
    }
}
