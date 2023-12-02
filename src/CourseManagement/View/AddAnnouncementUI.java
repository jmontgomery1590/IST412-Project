package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Announcement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddAnnouncementUI extends JFrame{
    private JFrame addAnnouncementFrame;
    private JPanel addAnnouncementPanel;
    private JButton saveButton, cancelButton;
    private JPanel announcementInfoPanel;
    private JTextField titleTextField;
    private JTextArea announcementTextArea;
    private JPanel buttonPanel;
    private JPanel titlePanel;
    private JPanel announcementPanel;
    private JScrollPane announcementScrollPane;
    private CourseMgmtController courseMgmtCntrl;
    private Announcement currentAnnouncement;


    public AddAnnouncementUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        currentAnnouncement = new Announcement("", "");
        initComponents();
    }

    private void initComponents() {
        addAnnouncementFrame = new JFrame("Add New Announcement");
        addAnnouncementFrame.setMinimumSize(new Dimension(800, 600));
        addAnnouncementFrame.setContentPane(addAnnouncementPanel);
        addAnnouncementFrame.setLocationRelativeTo(null);
        addAnnouncementFrame.setVisible(true);
        addAnnouncementFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addALAddAnnouncementButtons();
        addFocusListeners();
    }

    private void addALAddAnnouncementButtons()
    {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int courseTableID = courseMgmtCntrl.getSelectedCourse().getCourseTableID();
                String pageTitle = titleTextField.getText();
                String announcementContent = announcementTextArea.getText();
                courseMgmtCntrl.getAnnouncementMgmtUI().setNewAnnouncement(new Announcement(courseTableID, pageTitle, announcementContent));

                courseMgmtCntrl.getAnnouncementList().getAnnouncements().add(courseMgmtCntrl.getAnnouncementMgmtUI().getNewAnnouncement());
                courseMgmtCntrl.getDatabase().addAnnouncementToDatabase(courseMgmtCntrl.getAnnouncementMgmtUI());

                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                courseMgmtCntrl.getAnnouncementTable().fireTableDataChanged();
                addAnnouncementFrame.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                addAnnouncementFrame.dispose();
                courseMgmtCntrl.setAddAnnouncementUI(null);
            }
        });
    }

    private void onWindowClosing() {
        courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
        courseMgmtCntrl.getHomepageController().getHomepageUI().transferFocus();
    }

    private void addFocusListeners()
    {
        addAnnouncementFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
}
