package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;

import javax.swing.*;

public class ViewAnnouncementUI {
    private JPanel announcementPanel, announcementViewPanel;
    private JLabel announcementPanelAnnouncementLabel;
    private CourseMgmtController courseMgmtCntrl;

    public JPanel getAnnouncementViewPanel() {
        return announcementViewPanel;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}
