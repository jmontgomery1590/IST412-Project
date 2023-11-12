package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnouncementMgmtUI implements ActionListener {
    private JFrame announcementFrame = new JFrame("Announcements");
    private JPanel announcementMgmtPanel;
    private JPanel crudPanel;
    private JButton addAnnouncementButton;
    private JButton editAnnouncementButton;
    private JButton viewAnnouncementButton;
    private JButton deleteAnnouncementButton;
    private JTable announcementTable;
    private CourseMgmtController courseMgmtCntrl;
    private AddAnnouncementUI addAnnouncementUI;
    private HomepageController homepageController;
    private ViewAnnouncementUI viewAnnouncementUI;

    public AnnouncementMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;

        //announcementTable.setModel(courseMgmtCntrl.getAnnouncementTable());
        announcementFrame = new JFrame("Announcements");
        this.addALAnnouncementButtons();
    }

    public void addALAnnouncementButtons() {
        this.getViewAnnouncementButton().addActionListener(this);
        this.getAddAnnouncementButton().addActionListener(this);
        this.getEditAnnouncementButton().addActionListener(this);
        this.getDeleteAnnouncementButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == this.getViewAnnouncementButton())
        {
            if(this.getViewAnnouncementUI() != null)
            {
                this.setViewAnnouncementUI(new ViewAnnouncementUI());
                this.homepageController.getHomepageUI().getViewPanel().add(this.viewAnnouncementUI.getAnnouncementPanel(), "View Lesson");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Lesson");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }

        if (obj == this.getAddAnnouncementButton())
        {
            this.addAnnouncementUI = new AddAnnouncementUI(this.courseMgmtCntrl);
            this.addAnnouncementUI.addALNewAnnouncementButtons();
            this.homepageController.getHomepageUI().getHomeFrame().setEnabled(false);
        }


    }

    public JFrame getAnnouncementFrame() {
        return announcementFrame;
    }

    public void setAnnouncementFrame(JFrame announcementFrame) {
        this.announcementFrame = announcementFrame;
    }

    public JPanel getAnnouncementMgmtPanel() {
        return announcementMgmtPanel;
    }

    public void setAnnouncementMgmtPanel(JPanel announcementMgmtPanel) {
        this.announcementMgmtPanel = announcementMgmtPanel;
    }

    public JButton getAddAnnouncementButton() {
        return addAnnouncementButton;
    }

    public JButton getEditAnnouncementButton() {
        return editAnnouncementButton;
    }

    public JButton getViewAnnouncementButton() {
        return viewAnnouncementButton;
    }

    public JButton getDeleteAnnouncementButton() {
        return deleteAnnouncementButton;
    }

    public JTable getAnnouncementTable() {
        return announcementTable;
    }

    public void setAnnouncementTable(JTable announcementTable) {
        this.announcementTable = announcementTable;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }

    public AddAnnouncementUI getAddAnnouncementUI() {
        return addAnnouncementUI;
    }

    public void setAddAnnouncementUI(AddAnnouncementUI addAnnouncementUI) {
        this.addAnnouncementUI = addAnnouncementUI;
    }

    public HomepageController getHomepageController() {
        return homepageController;
    }

    public void setHomepageController(HomepageController homepageController) {
        this.homepageController = homepageController;
    }

    public ViewAnnouncementUI getViewAnnouncementUI() {
        return viewAnnouncementUI;
    }

    public void setViewAnnouncementUI(ViewAnnouncementUI viewAnnouncementUI) {
        this.viewAnnouncementUI = viewAnnouncementUI;
    }
}
