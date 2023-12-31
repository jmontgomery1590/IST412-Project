package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Lesson;
import CourseManagement.Model.LessonTableModel;
import UserAuthentication.Controller.HomepageController;
import UserAuthentication.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LessonMgmtUI {
    private JFrame lessonMgmtFrame;
    private JPanel buttonPanel, lessonMgmtPanel, lessonTablePanel;
    private JButton newLessonButton, editLessonButton, viewLessonButton, deleteLessonButton;
    private JTable lessonTable;
    private JLabel lessonLabel;
    private JScrollPane tableScrollPane;
    private CourseMgmtController courseMgmtCntrl;
    private int lessonListPosition;
    private Lesson newLesson, selectedLesson;
    private User user;

    public LessonMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        user = courseMgmtController.getHomepageController().getUser();
        courseMgmtCntrl.loadLessonList();
        courseMgmtCntrl.setLessonTable(new LessonTableModel(courseMgmtCntrl.getLessonList().getLessons()));
        lessonLabel.setText(courseMgmtCntrl.getSelectedCourse().getCourseID() + " " + courseMgmtCntrl.getSelectedCourse().getCourseName());
        lessonTable.setModel(courseMgmtCntrl.getLessonTable());
        lessonMgmtFrame = new JFrame("Lessons");
        addALLessonButtons();
        verifyButtonAccess();
    }

    private void verifyButtonAccess() {
        if (user.getRoleID().equals("4"))
        {
            newLessonButton.setVisible(false);
            deleteLessonButton.setVisible(false);
            editLessonButton.setVisible(false);
        }
        else if (user.getRoleID().equals("3"))
        {
            newLessonButton.setVisible(false);
            deleteLessonButton.setVisible(false);
        }
    }

    public void addALLessonButtons() {
        this.getViewLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessonListPosition = lessonTable.getSelectedRow();
                courseMgmtCntrl.setViewLessonUI(new ViewLessonUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().add(courseMgmtCntrl.getViewLessonUI().getLessonPanel(), "View Lesson");

                courseMgmtCntrl.getHomepageController().getHomepageUI().getCardSwapper().show(courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel(), "View Lesson");
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
        this.getNewLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setAddLessonUI(new AddLessonUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
            }
        });
        this.getEditLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessonListPosition = lessonTable.getSelectedRow();
                courseMgmtCntrl.setEditLessonUI(new EditLessonUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
            }
        });
        this.getDeleteLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = lessonTable.getSelectedRow();
                selectedLesson = courseMgmtCntrl.getLessonList().getLessons().get(selectedRow);
                courseMgmtCntrl.getDatabase().deleteLessonFromDatabase(selectedLesson);
                courseMgmtCntrl.getLessonList().getLessons().remove(selectedRow);
                courseMgmtCntrl.getLessonTable().fireTableDataChanged();
            }
        });
    }

    public JButton getNewLessonButton() {
        return newLessonButton;
    }

    public JButton getEditLessonButton() {
        return editLessonButton;
    }

    public JButton getViewLessonButton() {
        return viewLessonButton;
    }

    public JButton getDeleteLessonButton() {
        return deleteLessonButton;
    }

    public JPanel getLessonMgmtPanel() {
        return lessonMgmtPanel;
    }

    public int getLessonListPosition() {
        return lessonListPosition;
    }

    public Lesson getNewLesson() {
        return newLesson;
    }

    public void setNewLesson(Lesson newLesson) {
        this.newLesson = newLesson;
    }
}
