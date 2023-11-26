package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Lesson;
import CourseManagement.Model.LessonList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLessonUI {
    private JPanel lessonPanel, viewLessonPanel, readingPanel, titlePanel, contentPanel, buttonPanel;
    private JTextArea lessonReadingArea;
    private JTextField titleTextField;
    private JTextArea lessonContentArea;
    private JButton previousLessonButton, nextLessonButton;
    private JScrollPane lessonContentScrollPane;
    private JLabel lessonTitleLabel;
    private JScrollPane lessonReadingScrollPane;
    private CourseMgmtController courseMgmtCntrl;
    private Lesson prevLesson;
    private Lesson nextLesson;
    private Lesson currentLesson;
    private LessonList lessonList;
    private int lessonNumber;

    public ViewLessonUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        lessonNumber = courseMgmtCntrl.getLessonMgmtUI().getLessonListPosition();
        setLessons(lessonNumber);
        setLessonText();
        addALViewLessonButtons();
        checkNextAndPrevButtons();
    }

    public void setLessonText() {
        lessonTitleLabel.setText(currentLesson.getPageTitle());
        lessonContentArea.setText(currentLesson.getLessonContent());
        lessonContentArea.setEditable(false);
        lessonContentArea.setCaretPosition(0);
        lessonReadingArea.setText(currentLesson.getAssignedReading());
        lessonReadingArea.setEditable(false);
    }

    public void setLessons(int lessonListNumber) {
        lessonNumber = lessonListNumber;
        lessonList = courseMgmtCntrl.getLessonList();
        currentLesson = lessonList.getLessons().get(lessonNumber);

        if (lessonNumber > 0)
        {
            prevLesson = lessonList.getLessons().get(lessonListNumber - 1);
        }
        else
        {
            prevLesson = null;
        }

        if (lessonListNumber + 1 < lessonList.getLessons().size())
        {
            nextLesson = lessonList.getLessons().get(lessonListNumber + 1);
        }
        else
        {
            nextLesson = null;
        }
    }

    public void checkNextAndPrevButtons() {
        previousLessonButton.setEnabled(prevLesson != null);
        nextLessonButton.setEnabled(nextLesson != null);
    }

    public void addALViewLessonButtons() {
        nextLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nextLesson != null)
                {
                    setLessons(lessonNumber + 1);
                    setLessonText();
                    checkNextAndPrevButtons();
                }
            }
        });

        previousLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prevLesson != null)
                {
                    setLessons(lessonNumber - 1);
                    setLessonText();
                    checkNextAndPrevButtons();
                }
            }
        });
    }

    public JPanel getLessonPanel() {
        return lessonPanel;
    }

    public void setLessonPanel(JPanel lessonPanel) {
        this.lessonPanel = lessonPanel;
    }

    public JPanel getViewLessonPanel() {
        return viewLessonPanel;
    }

    public void setViewLessonPanel(JPanel viewLessonPanel) {
        this.viewLessonPanel = viewLessonPanel;
    }

    public JPanel getReadingPanel() {
        return readingPanel;
    }

    public void setReadingPanel(JPanel readingPanel) {
        this.readingPanel = readingPanel;
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

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JTextArea getLessonReadingArea() {
        return lessonReadingArea;
    }

    public void setLessonReadingArea(JTextArea lessonReadingArea) {
        this.lessonReadingArea = lessonReadingArea;
    }

    public JTextField getTitleTextField() {
        return titleTextField;
    }

    public void setTitleTextField(JTextField titleTextField) {
        this.titleTextField = titleTextField;
    }

    public JTextArea getLessonContentArea() {
        return lessonContentArea;
    }

    public void setLessonContentArea(JTextArea lessonContentArea) {
        this.lessonContentArea = lessonContentArea;
    }

    public JButton getPreviousLessonButton() {
        return previousLessonButton;
    }

    public void setPreviousLessonButton(JButton previousLessonButton) {
        this.previousLessonButton = previousLessonButton;
    }

    public JButton getNextLessonButton() {
        return nextLessonButton;
    }

    public void setNextLessonButton(JButton nextLessonButton) {
        this.nextLessonButton = nextLessonButton;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }

    public Lesson getPrevLesson() {
        return prevLesson;
    }

    public void setPrevLesson(Lesson prevLesson) {
        this.prevLesson = prevLesson;
    }

    public Lesson getNextLesson() {
        return nextLesson;
    }

    public void setNextLesson(Lesson nextLesson) {
        this.nextLesson = nextLesson;
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

    public JScrollPane getLessonContentScrollPane() {
        return lessonContentScrollPane;
    }

    public void setLessonContentScrollPane(JScrollPane lessonContentScrollPane) {
        this.lessonContentScrollPane = lessonContentScrollPane;
    }
}
