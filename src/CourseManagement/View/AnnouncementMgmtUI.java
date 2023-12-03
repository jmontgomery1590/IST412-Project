package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Announcement;
import CourseManagement.Model.AnnouncementTableModel;
import UserAuthentication.Controller.HomepageController;
import UserAuthentication.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnouncementMgmtUI {
    private HomepageController homepageController;
    private AnnouncementMgmtUI announcementMgmtUI;
    private JFrame announcementFrame;
    private JPanel announcementTablePanel, announcementMgmtPanel, buttonPanel, announcementTitlePanel;
    private JButton newAnnouncementButton, editAnnouncementButton, viewAnnouncementButton, deleteAnnouncementButton;
    private JTable announcementTable;
    private JLabel announcementLabel;
    private JScrollPane tableScrollPane;
    private CourseMgmtController courseMgmtCntrl;
    private int announcementListPosition;
    private Announcement newAnnouncement, selectedAnnouncement;
    private User user;

    public AnnouncementMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        user = courseMgmtController.getHomepageController().getUser();
        courseMgmtCntrl.loadAnnouncementList();
        courseMgmtCntrl.setAnnouncementTable(new AnnouncementTableModel(courseMgmtCntrl.getAnnouncementList().getAnnouncements()));
        announcementLabel.setText(courseMgmtCntrl.getSelectedCourse().getCourseID() + " " + courseMgmtCntrl.getSelectedCourse().getCourseName());
        announcementTable.setModel(courseMgmtCntrl.getAnnouncementTable());
        announcementFrame = new JFrame("Announcements");
        addALAnnouncementButtons();
        verifyButtonAccess();
    }

    private void verifyButtonAccess() {
        if (user.getRoleID().equals("4"))
        {
            newAnnouncementButton.setVisible(false);
            deleteAnnouncementButton.setVisible(false);
            editAnnouncementButton.setVisible(false);
        }
    }

    public void addALAnnouncementButtons() {
        this.getViewAnnouncementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                announcementListPosition = announcementTable.getSelectedRow();
                courseMgmtCntrl.setViewAnnouncementUI(new ViewAnnouncementUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().add(courseMgmtCntrl.getViewAnnouncementUI().getAnnouncementPanel(), "View Announcement");

                courseMgmtCntrl.getHomepageController().getHomepageUI().getCardSwapper().show(courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel(), "View Announcement");
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
        this.getNewAnnouncementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setAddAnnouncementUI(new AddAnnouncementUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
            }
        });
        this.getEditAnnouncementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                announcementListPosition = announcementTable.getSelectedRow();
                courseMgmtCntrl.setEditAnnouncementUI(new EditAnnouncementUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
            }
        });
        this.getDeleteAnnouncementButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = announcementTable.getSelectedRow();
                selectedAnnouncement = courseMgmtCntrl.getAnnouncementList().getAnnouncements().get(selectedRow);
                courseMgmtCntrl.getDatabase().deleteAnnouncementFromDatabase(selectedAnnouncement);
                courseMgmtCntrl.getAnnouncementList().getAnnouncements().remove(selectedRow);
                courseMgmtCntrl.getAnnouncementTable().fireTableDataChanged();
            }
        });
    }

    public JPanel getAnnouncementMgmtPanel() {
        return announcementMgmtPanel;
    }

    public JButton getNewAnnouncementButton() {
        return newAnnouncementButton;
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

    public int getAnnouncementListPosition() {
        return announcementListPosition;
    }

    public Announcement getNewAnnouncement() {
        return newAnnouncement;
    }

    public void setNewAnnouncement(Announcement newAnnouncement) {
        this.newAnnouncement = newAnnouncement;
    }
}
