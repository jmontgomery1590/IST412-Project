package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Lesson;
import CourseManagement.Model.LessonList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLessonUI {
    private JPanel lessonPanel, viewLessonPanel, readingPanel, titlePanel, contentPanel, buttonPanel;
    private JTextArea lessonReadingArea, lessonContentArea;
    private JTextField titleTextField;
    private JButton previousLessonButton, nextLessonButton;
    private JScrollPane lessonContentScrollPane, lessonReadingScrollPane;
    private JLabel lessonTitleLabel;
    private CourseMgmtController courseMgmtCntrl;
    private Lesson prevLesson, nextLesson, currentLesson;
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
        lessonReadingArea.setCaretPosition(0);
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

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }
}
