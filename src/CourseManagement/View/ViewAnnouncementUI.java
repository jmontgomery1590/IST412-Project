package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Announcement;
import CourseManagement.Model.AnnouncementList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAnnouncementUI {
    private JFrame viewAnnouncementFrame;
    private JPanel announcementPanel, viewAnnouncementPanel, announcementInfoPanel;
    private JTextField titleTextField;
    private JTextArea announcementTextArea;
    private JButton previousAnnouncementButton, nextAnnouncementButton;
    private JLabel titleTextLabel;
    private CourseMgmtController courseMgmtCntrl;
    private Announcement prevAnnouncement;
    private Announcement nextAnnouncement;
    private Announcement currentAnnouncement;
    private AnnouncementList announcementList;
    private int announcementNumber;

    public ViewAnnouncementUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        announcementNumber = 0;
        setAnnouncements(0);
        addALViewAnnouncementButtons();
        fillAnnouncementTitle();
        fillAnnouncementBody();
        checkNextAndPrevButtons();
    }

    public void fillAnnouncementTitle() {
        titleTextLabel.setText(courseMgmtCntrl.getAnnouncement().getPageTitle());
    }

    public void fillAnnouncementBody() {
        announcementTextArea.setText(courseMgmtCntrl.getAnnouncement().getAnnouncementBody());
    }

    public void setAnnouncements (int announcementListnumber) {
        announcementNumber = announcementListnumber;
        announcementList = courseMgmtCntrl.getAnnouncementList();
        currentAnnouncement = announcementList.getAnnouncements().get(announcementNumber);

        if (announcementNumber > 0)
        {
            prevAnnouncement = announcementList.getAnnouncements().get(announcementListnumber - 1);
        }
        else
        {
            prevAnnouncement = null;
        }

        if (announcementListnumber + 1 < announcementList.getAnnouncements().size())
        {
            nextAnnouncement = announcementList.getAnnouncements().get(announcementListnumber + 1);
        }
        else
        {
            nextAnnouncement = null;
        }
        fillAnnouncementTitle();
        fillAnnouncementBody();
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
                    checkNextAndPrevButtons();
                }
            }
        });
    }

    public JPanel getViewAnnouncementPanel() {
        return viewAnnouncementPanel;
    }

    public void setViewAnnouncementPanel(JPanel viewAnnouncementPanel) {
        this.viewAnnouncementPanel = viewAnnouncementPanel;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }

    public JPanel getAnnouncementPanel() {
        return announcementPanel;
    }

    public void setAnnouncementPanel(JPanel announcementPanel) {
        this.announcementPanel = announcementPanel;
    }
}
