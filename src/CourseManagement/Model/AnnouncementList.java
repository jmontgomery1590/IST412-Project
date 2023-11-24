package CourseManagement.Model;

import java.util.ArrayList;

public class AnnouncementList {
    private Announcement announcement;
    private ArrayList<Announcement> announcements = new ArrayList<>();

    public AnnouncementList() {
        if (this.getAnnouncements().isEmpty()) {
            createAnnouncementsList();
        }
    }

    public void createAnnouncementsList() {
        for (int i = 0; i <= 5; i++) {
            this.setAnnouncement(new Announcement("Announcement" + i, "Body " + i));
            this.getAnnouncements().add(this.getAnnouncement());
        }
    }

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
