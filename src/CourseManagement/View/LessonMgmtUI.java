package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.LessonTableModel;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LessonMgmtUI {
    private JFrame lessonMgmtFrame;
    private JPanel crudPanel;
    private JPanel lessonMgmtPanel, lessonTablePanel;
    private JButton addLessonButton, editLessonButton, viewLessonButton, deleteLessonButton;
    private JTable lessonTable;
    private JLabel lessonLabel;
    private CourseMgmtController courseMgmtCntrl;

    public LessonMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        courseMgmtCntrl.setLessonList(courseMgmtController.getSelectedCourse().getLessonList());
        courseMgmtCntrl.setLessonTable(new LessonTableModel(courseMgmtCntrl.getLessonList().getLessons()));
        lessonLabel.setText(courseMgmtCntrl.getSelectedCourse().getCourseID() + " " + courseMgmtCntrl.getSelectedCourse().getCourseName());
        lessonTable.setModel(courseMgmtCntrl.getLessonTable());
        lessonMgmtFrame = new JFrame("Lessons");
        addALLessonButtons();
    }

    public void addALLessonButtons() {
        this.getViewLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setViewLessonUI(new ViewLessonUI());
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().add(courseMgmtCntrl.getViewLessonUI().getLessonViewPanel(), "View Lesson");

                courseMgmtCntrl.getHomepageController().getHomepageUI().getCardSwapper().show(courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel(), "View Lesson");
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
        this.getAddLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setAddLessonUI(new AddLessonUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
            }
        });
        this.getEditLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.getDeleteLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JButton getAddLessonButton() {
        return addLessonButton;
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
}
