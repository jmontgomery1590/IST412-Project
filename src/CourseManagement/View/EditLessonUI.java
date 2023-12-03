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
    private JPanel editLessonPanel, readingPanel, buttonPanel, titlePanel, contentPanel;
    private JTextField titleTextField;
    private JTextArea lessonReadingArea, lessonContentArea;
    private JButton saveButton, cancelButton;
    private JScrollPane lessonContentScrollPane, lessonReadingScrollPane;
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
        editLessonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.courseMgmtController = courseMgmtController;
        lessonNumber = this.courseMgmtController.getLessonMgmtUI().getLessonListPosition();
        setLessons(lessonNumber);
        setLessonText();
        addALEditAnnouncementButtons();
    }

    public void setLessons (int lessonListNumber) {
        lessonNumber = lessonListNumber;
        lessonList = courseMgmtController.getLessonList();
        currentLesson = lessonList.getLessons().get(lessonNumber);
    }

    public void setLessonText() {
        titleTextField.setText(currentLesson.getPageTitle());
        lessonContentArea.setText(currentLesson.getLessonContent());
        lessonContentArea.setCaretPosition(0);
        lessonReadingArea.setText((currentLesson.getAssignedReading()));
        lessonReadingArea.setCaretPosition(0);
    }

    public void addALEditAnnouncementButtons() {
        this.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentLesson.setPageTitle(titleTextField.getText());
                currentLesson.setLessonContent(lessonContentArea.getText());
                currentLesson.setAssignedReading(lessonReadingArea.getText());

                courseMgmtController.getDatabase().editLessonInDatabase(currentLesson);
                courseMgmtController.getLessonTable().fireTableDataChanged();

                courseMgmtController.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                editLessonFrame.dispose();
                courseMgmtController.setEditLessonUI(null);
            }
        });
        this.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtController.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                editLessonFrame.dispose();
                courseMgmtController.setEditLessonUI(null);
            }
        });
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
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
}
