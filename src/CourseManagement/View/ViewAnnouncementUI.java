package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Announcement;
import CourseManagement.Model.AnnouncementList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAnnouncementUI {
    private JFrame viewAnnouncementFrame;
    private JPanel announcementPanel, viewAnnouncementPanel, announcementInfoPanel, announcementTextPanel, buttonPanel;
    private JTextField titleTextField;
    private JTextArea announcementTextArea;
    private JButton previousAnnouncementButton, nextAnnouncementButton;
    private JLabel titleTextLabel;
    private JScrollPane announcementScrollPane;
    private CourseMgmtController courseMgmtCntrl;
    private Announcement prevAnnouncement, nextAnnouncement, currentAnnouncement;
    private AnnouncementList announcementList;
    private int announcementNumber;

    public ViewAnnouncementUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        announcementNumber = courseMgmtCntrl.getAnnouncementMgmtUI().getAnnouncementListPosition();
        setAnnouncements(announcementNumber);
        setAnnouncementText();
        addALViewAnnouncementButtons();
        checkNextAndPrevButtons();
    }

    public void setAnnouncementText() {
        titleTextLabel.setText(currentAnnouncement.getPageTitle());
        announcementTextArea.setText(currentAnnouncement.getAnnouncementContent());
        announcementTextArea.setEditable(false);
        announcementTextArea.setCaretPosition(0);
    }

    public void setAnnouncements (int announcementListNumber) {
        announcementNumber = announcementListNumber;
        announcementList = courseMgmtCntrl.getAnnouncementList();
        currentAnnouncement = announcementList.getAnnouncements().get(announcementNumber);

        if (announcementNumber > 0)
        {
            prevAnnouncement = announcementList.getAnnouncements().get(announcementListNumber - 1);
        }
        else
        {
            prevAnnouncement = null;
        }

        if (announcementListNumber + 1 < announcementList.getAnnouncements().size())
        {
            nextAnnouncement = announcementList.getAnnouncements().get(announcementListNumber + 1);
        }
        else
        {
            nextAnnouncement = null;
        }
    }

    public void checkNextAndPrevButtons() {
        previousAnnouncementButton.setEnabled(prevAnnouncement != null);
        nextAnnouncementButton.setEnabled(nextAnnouncement != null);
    }

    public void addALViewAnnouncementButtons() {
        nextAnnouncementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nextAnnouncement != null)
                {
                    setAnnouncements(announcementNumber + 1);
                    setAnnouncementText();
                    checkNextAndPrevButtons();
                }
            }
        });

        previousAnnouncementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prevAnnouncement != null)
                {
                    setAnnouncements(announcementNumber - 1);
                    setAnnouncementText();
                    checkNextAndPrevButtons();
                }
            }
        });
    }

    public JPanel getAnnouncementPanel() {
        return announcementPanel;
    }
}
