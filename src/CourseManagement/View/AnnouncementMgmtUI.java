package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.AnnouncementTableModel;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnouncementMgmtUI {
    private JFrame announcementFrame;
    private JPanel crudPanel;
    private JPanel announcementTablePanel, announcementMgmtPanel;
    private JButton addAnnouncementButton, editAnnouncementButton, viewAnnouncementButton, deleteAnnouncementButton;
    private JTable announcementTable;
    private JLabel announcementLabel;
    private CourseMgmtController courseMgmtCntrl;


    public AnnouncementMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        courseMgmtCntrl.setAnnouncementList(courseMgmtCntrl.getSelectedCourse().getAnnouncementList());
        courseMgmtCntrl.setAnnouncementTable(new AnnouncementTableModel(courseMgmtCntrl.getAnnouncementList().getAnnouncements()));
        announcementLabel.setText(courseMgmtCntrl.getSelectedCourse().getCourseID() + " " + courseMgmtCntrl.getSelectedCourse().getCourseName());
        announcementTable.setModel(courseMgmtCntrl.getAnnouncementTable());
        announcementFrame = new JFrame("Announcements");
        addALAnnouncementButtons();
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

    public JPanel getAnnouncementMgmtPanel() {
        return announcementMgmtPanel;
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

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}
