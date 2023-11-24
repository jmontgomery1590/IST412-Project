package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.AnnouncementTableModel;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnouncementMgmtUI {
    private HomepageController homepageController;
    private AnnouncementMgmtUI announcementMgmtUI;
    private JFrame announcementFrame;
    private JPanel buttonPanel;
    private JPanel announcementTablePanel, announcementMgmtPanel;
    private JButton addAnnouncementButton, editAnnouncementButton, viewAnnouncementButton, deleteAnnouncementButton;
    private JTable announcementTable;
    private JLabel announcementLabel;
    private JPanel announcementTitlePanel;
    private JScrollPane tableScrollPane;
    private CourseMgmtController courseMgmtCntrl;


    public AnnouncementMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        courseMgmtCntrl.setAnnouncementList(courseMgmtCntrl.getSelectedCourse().getAnnouncementList());
        courseMgmtCntrl.setAnnouncementTable(new AnnouncementTableModel(courseMgmtCntrl.getAnnouncementList().getAnnouncements()));
        announcementLabel.setText(courseMgmtCntrl.getSelectedCourse().getCourseID() + " " + courseMgmtCntrl.getSelectedCourse().getCourseName());
        announcementTable.setModel(courseMgmtCntrl.getAnnouncementTable());
        announcementFrame = new JFrame("Announcements");
        addALAnnouncementButtons();
        //verifyButtonAccess();
    }

    /*private void verifyButtonAccess() {
        if (homepageController.getUser().getLoginID().equalsIgnoreCase("Student") || homepageController.getUser().getLoginID().equalsIgnoreCase("TA"))
        {
            announcementMgmtUI.getAddAnnouncementButton().setVisible(false);
            announcementMgmtUI.getDeleteAnnouncementButton().setVisible(false);
            announcementMgmtUI.getEditAnnouncementButton().setVisible(false);
        }
        else if (homepageController.getUser().getLoginID().equalsIgnoreCase("Instructor"))
        {
            announcementMgmtUI.getAddAnnouncementButton().setVisible(false);
            announcementMgmtUI.getDeleteAnnouncementButton().setVisible(false);
        }
    }*/

    public void addALAnnouncementButtons() {
        this.viewAnnouncementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = announcementTable.getSelectedRow();
                courseMgmtCntrl.setAnnouncement(courseMgmtCntrl.getAnnouncementList().getAnnouncements().get(selectedRow));
                courseMgmtCntrl.setViewAnnouncementUI(new ViewAnnouncementUI(courseMgmtCntrl));

                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().add(courseMgmtCntrl.getViewAnnouncementUI().getAnnouncementPanel(), "View Announcement");
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
                int selectedRow = announcementTable.getSelectedRow();
                courseMgmtCntrl.getAnnouncementList().getAnnouncements().remove(selectedRow);
                courseMgmtCntrl.getAnnouncementTable().fireTableDataChanged();
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
