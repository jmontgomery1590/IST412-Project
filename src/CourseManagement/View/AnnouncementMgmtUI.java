package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.AnnouncementTableModel;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnouncementMgmtUI {
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
        courseMgmtCntrl.setAnnouncementList(courseMgmtCntrl.getSelectedCourse().getAnnouncementList());
        courseMgmtCntrl.setAnnouncementTable(new AnnouncementTableModel(courseMgmtCntrl.getAnnouncementList().getAnnouncements()));
        announcementTable.setModel(courseMgmtCntrl.getAnnouncementTable());
        announcementFrame = new JFrame("Announcements");
        this.addALAnnouncementButtons();
    }

    public void addALAnnouncementButtons() {
        this.getViewAnnouncementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setViewAnnouncementUI(new ViewAnnouncementUI());
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().add(courseMgmtCntrl.getViewAnnouncementUI().getAnnouncementViewPanel(), "Announcement View");

                courseMgmtCntrl.getHomepageController().getHomepageUI().getCardSwapper().show(courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel(), "Announcement View");
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
        this.getAddAnnouncementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setAddAnnouncementUI(new AddAnnouncementUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
            }
        });
        this.getEditAnnouncementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.getDeleteAnnouncementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
