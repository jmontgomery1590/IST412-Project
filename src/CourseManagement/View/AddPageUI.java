package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddPageUI extends JFrame{
    private JFrame addPageFrame;
    private JButton selectButton;
    private JButton cancelButton;
    private JLabel addPageLabel;
    private JRadioButton announcementRadioButton;
    private JRadioButton lessonModuleRadioButton;
    private JRadioButton assignmentRadioButton;
    private JPanel addPagePanel;
    private JPanel buttonPanel;
    private JPanel optionPanel;
    private CourseMgmtController courseMgmtCntrl;

    public AddPageUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        addPageFrame = new JFrame("Add New Page");
        addPageFrame.setResizable(false);
        addPageFrame.setMinimumSize(new Dimension(800,600));
        addPageFrame.setContentPane(addPagePanel);
        addPageFrame.setLocationRelativeTo(null);
        addPageFrame.setVisible(true);
        addPageFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void addALNewPageButtons() {
        this.getSelectButton().addActionListener(this.courseMgmtCntrl);
        this.getCancelButton().addActionListener(this.courseMgmtCntrl);
    }

    public JFrame getAddPageFrame() {
        return addPageFrame;
    }

    public void setAddPageFrame(JFrame addPageFrame) {
        this.addPageFrame = addPageFrame;
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public void setSelectButton(JButton selectButton) {
        this.selectButton = selectButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JLabel getAddPageLabel() {
        return addPageLabel;
    }

    public void setAddPageLabel(JLabel addPageLabel) {
        this.addPageLabel = addPageLabel;
    }

    public JRadioButton getAnnouncementRadioButton() {
        return announcementRadioButton;
    }

    public void setAnnouncementRadioButton(JRadioButton announcementRadioButton) {
        this.announcementRadioButton = announcementRadioButton;
    }

    public JRadioButton getLessonModuleRadioButton() {
        return lessonModuleRadioButton;
    }

    public void setLessonModuleRadioButton(JRadioButton lessonModuleRadioButton) {
        this.lessonModuleRadioButton = lessonModuleRadioButton;
    }

    public JRadioButton getAssignmentRadioButton() {
        return assignmentRadioButton;
    }

    public void setAssignmentRadioButton(JRadioButton assignmentRadioButton) {
        this.assignmentRadioButton = assignmentRadioButton;
    }

    public JPanel getAddPagePanel() {
        return addPagePanel;
    }

    public void setAddPagePanel(JPanel addPagePanel) {
        this.addPagePanel = addPagePanel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JPanel getOptionPanel() {
        return optionPanel;
    }

    public void setOptionPanel(JPanel optionPanel) {
        this.optionPanel = optionPanel;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}
