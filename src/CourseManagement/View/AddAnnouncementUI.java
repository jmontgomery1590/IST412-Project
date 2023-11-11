package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddAnnouncementUI extends JFrame{
    private JFrame addAnnouncementFrame;
    private JPanel addAnnouncementPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel announcementInfoPanel;
    private JTextField titleTextField;
    private JTextArea announcementTextArea;
    private CourseMgmtController courseMgmtCntrl;

    public AddAnnouncementUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        addAnnouncementFrame = new JFrame("Add New Lesson");
        addAnnouncementFrame.setResizable(false);
        addAnnouncementFrame.setMinimumSize(new Dimension(800, 600));
        addAnnouncementFrame.setContentPane(addAnnouncementPanel);
        addAnnouncementFrame.setLocationRelativeTo(null);
        addAnnouncementFrame.setVisible(true);
        addAnnouncementFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public JFrame getAddAnnouncementFrame() {
        return addAnnouncementFrame;
    }

    public void setAddAnnouncementFrame(JFrame addAnnouncementFrame) {
        this.addAnnouncementFrame = addAnnouncementFrame;
    }

    public JPanel getAddAnnouncementPanel() {
        return addAnnouncementPanel;
    }

    public void setAddAnnouncementPanel(JPanel addAnnouncementPanel) {
        this.addAnnouncementPanel = addAnnouncementPanel;
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

    public JPanel getAnnouncementInfoPanel() {
        return announcementInfoPanel;
    }

    public void setAnnouncementInfoPanel(JPanel announcementInfoPanel) {
        this.announcementInfoPanel = announcementInfoPanel;
    }

    public JTextField getTitleTextField() {
        return titleTextField;
    }

    public void setTitleTextField(JTextField titleTextField) {
        this.titleTextField = titleTextField;
    }

    public JTextArea getAnnouncementTextArea() {
        return announcementTextArea;
    }

    public void setAnnouncementTextArea(JTextArea announcementTextArea) {
        this.announcementTextArea = announcementTextArea;
    }
}
