package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddLessonUI extends JFrame{
    private JFrame addLessonFrame;
    private JTextField titleTextField;
    private JTextArea contentTextArea;
    private JTextArea readingTextArea;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel addLessonPanel;
    private JPanel readingPanel;
    private JPanel titlePanel;
    private JPanel contentPanel;
    CourseMgmtController courseMgmtCntrl;

    public AddLessonUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        addLessonFrame = new JFrame("Add New Lesson");
        addLessonFrame.setResizable(false);
        addLessonFrame.setMinimumSize(new Dimension(800, 600));
        addLessonFrame.setContentPane(addLessonPanel);
        addLessonFrame.setLocationRelativeTo(null);
        addLessonFrame.setVisible(true);
        addLessonFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void addALNewLessonButtons() {
        this.getSaveButton().addActionListener(this.courseMgmtCntrl);
        this.getCancelButton().addActionListener(this.courseMgmtCntrl);
    }

    public JFrame getAddLessonFrame() {
        return addLessonFrame;
    }

    public void setAddLessonUI(JFrame addLessonFrame) {
        this.addLessonFrame = addLessonFrame;
    }

    public JTextField getTitleTextField() {
        return titleTextField;
    }

    public void setTitleTextField(JTextField titleTextField) {
        this.titleTextField = titleTextField;
    }

    public JTextArea getContentTextArea() {
        return contentTextArea;
    }

    public void setContentTextArea(JTextArea contentTextArea) {
        this.contentTextArea = contentTextArea;
    }

    public JTextArea getReadingTextArea() {
        return readingTextArea;
    }

    public void setReadingTextArea(JTextArea readingTextArea) {
        this.readingTextArea = readingTextArea;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
