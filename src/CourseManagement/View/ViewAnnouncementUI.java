package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;

import javax.swing.*;

public class ViewAnnouncementUI {
    private JPanel announcementPanel;
    private JPanel announcementViewPanel;
    private JLabel announcementPanelAnnouncementLabel;
    private CourseMgmtController courseMgmtCntrl;

    public JPanel getAnnouncementPanel() {
        return announcementPanel;
    }

    public void setAnnouncementPanel(JPanel announcementPanel) {
        this.announcementPanel = announcementPanel;
    }

    public JPanel getAnnouncementViewPanel() {
        return announcementViewPanel;
    }

    public void setAnnouncementViewPanel(JPanel announcementViewPanel) {
        this.announcementViewPanel = announcementViewPanel;
    }

    public JLabel getAnnouncementPanelAnnouncementLabel() {
        return announcementPanelAnnouncementLabel;
    }

    public void setAnnouncementPanelAnnouncementLabel(JLabel announcementPanelAnnouncementLabel) {
        this.announcementPanelAnnouncementLabel = announcementPanelAnnouncementLabel;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}
