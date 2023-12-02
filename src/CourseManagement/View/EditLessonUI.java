package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Lesson;
import CourseManagement.Model.LessonList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditLessonUI {
    private JFrame editLessonFrame;
    private JPanel editLessonPanel, readingPanel, buttonPanel;
    private JTextField titleTextField;
    private JTextArea lessonReadingArea, lessonContentArea;
    private JPanel titlePanel, contentPanel;
    private JButton saveButton, cancelButton;
    private JScrollPane lessonContentScrollPane;
    private JScrollPane lessonReadingScrollPane;
    private CourseMgmtController courseMgmtController;
    private Lesson currentLesson;
    private LessonList lessonList;
    private int lessonNumber;

    public EditLessonUI(CourseMgmtController courseMgmtController) {
        editLessonFrame = new JFrame("Edit Lesson");
        editLessonFrame.setMinimumSize(new Dimension(800, 600));
        editLessonFrame.setContentPane(editLessonPanel);
        editLessonFrame.setLocationRelativeTo(null);
        editLessonFrame.setVisible(true);
        editLessonFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.courseMgmtController = courseMgmtController;
        lessonNumber = this.courseMgmtController.getLessonMgmtUI().getLessonListPosition();
        setLessons(lessonNumber);
        setLessonText();
        addALEditAnnouncementButtons();
    }

    public void setLessonText() {
        titleTextField.setText(currentLesson.getPageTitle());
        lessonContentArea.setText(currentLesson.getLessonContent());
        lessonContentArea.setCaretPosition(0);
        lessonReadingArea.setText((currentLesson.getAssignedReading()));
        lessonReadingArea.setCaretPosition(0);
    }

    public void setLessons (int lessonListNumber) {
        lessonNumber = lessonListNumber;
        lessonList = courseMgmtController.getLessonList();
        currentLesson = lessonList.getLessons().get(lessonNumber);
    }

    public void addALEditAnnouncementButtons() {
        this.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lessonTitle = titleTextField.getText();
                String lessonText = lessonContentArea.getText();
                String readingText = lessonReadingArea.getText();

                //Insert logic here
            }
        });
        this.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtController.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                editLessonFrame.dispose();
                courseMgmtController.setEditCourseUI(null);
            }
        });
    }

    public JFrame getEditLessonFrame() {
        return editLessonFrame;
    }

    public void setEditLessonFrame(JFrame editLessonFrame) {
        this.editLessonFrame = editLessonFrame;
    }

    public JPanel getEditLessonPanel() {
        return editLessonPanel;
    }

    public void setEditLessonPanel(JPanel editLessonPanel) {
        this.editLessonPanel = editLessonPanel;
    }

    public JPanel getReadingPanel() {
        return readingPanel;
    }

    public void setReadingPanel(JPanel readingPanel) {
        this.readingPanel = readingPanel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JTextField getTitleTextField() {
        return titleTextField;
    }

    public void setTitleTextField(JTextField titleTextField) {
        this.titleTextField = titleTextField;
    }

    public JTextArea getLessonReadingArea() {
        return lessonReadingArea;
    }

    public void setLessonReadingArea(JTextArea lessonReadingArea) {
        this.lessonReadingArea = lessonReadingArea;
    }

    public JTextArea getLessonContentArea() {
        return lessonContentArea;
    }

    public void setLessonContentArea(JTextArea lessonContentArea) {
        this.lessonContentArea = lessonContentArea;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
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

    public CourseMgmtController getCourseMgmtController() {
        return courseMgmtController;
    }

    public void setCourseMgmtController(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
    }

    public Lesson getCurrentLesson() {
        return currentLesson;
    }

    public void setCurrentLesson(Lesson currentLesson) {
        this.currentLesson = currentLesson;
    }

    public LessonList getLessonList() {
        return lessonList;
    }

    public void setLessonList(LessonList lessonList) {
        this.lessonList = lessonList;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
}
