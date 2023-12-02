package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Announcement;
import CourseManagement.Model.AnnouncementList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAnnouncementUI {
    private JFrame editAnnouncementFrame;
    private JPanel editAnnouncementPanel, announcementInfoPanel;
    private JTextField titleTextField;
    private JTextArea announcementTextArea;
    private JPanel buttonPanel;
    private JButton saveButton, cancelButton;
    private JPanel titlePanel;
    private JPanel announcementPanel;
    private JScrollPane announcementScrollPane;
    private CourseMgmtController courseMgmtController;
    private Announcement currentAnnouncement;
    private AnnouncementList announcementList;
    private int announcementNumber;

    public EditAnnouncementUI(CourseMgmtController courseMgmtController) {
        editAnnouncementFrame = new JFrame("Edit Announcement");
        editAnnouncementFrame.setMinimumSize(new Dimension(800, 600));
        editAnnouncementFrame.setContentPane(editAnnouncementPanel);
        editAnnouncementFrame.setLocationRelativeTo(null);
        editAnnouncementFrame.setVisible(true);
        editAnnouncementFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.courseMgmtController = courseMgmtController;
        announcementNumber = this.courseMgmtController.getAnnouncementMgmtUI().getAnnouncementListPosition();
        setAnnouncements(announcementNumber);
        setAnnouncementText();
        addALEditAnnouncementButtons();
    }

    public void setAnnouncementText() {
        titleTextField.setText(currentAnnouncement.getPageTitle());
        announcementTextArea.setText(currentAnnouncement.getAnnouncementContent());
        announcementTextArea.setCaretPosition(0);
    }

    public void setAnnouncements (int announcementListNumber) {
        announcementNumber = announcementListNumber;
        announcementList = courseMgmtController.getAnnouncementList();
        currentAnnouncement = announcementList.getAnnouncements().get(announcementNumber);
    }

    public void addALEditAnnouncementButtons() {
        this.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String announcementTitle = titleTextField.getText();
                String announcementText = announcementTextArea.getText();

                //Insert logic here
            }
        });
        this.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtController.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                editAnnouncementFrame.dispose();
                courseMgmtController.setEditCourseUI(null);
            }
        });
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
}
