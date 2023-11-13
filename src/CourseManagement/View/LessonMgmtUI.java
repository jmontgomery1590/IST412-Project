package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.LessonTableModel;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LessonMgmtUI {
    private JFrame lessonMgmtFrame = new JFrame("Lessons");
    private JPanel crudPanel;
    private JButton addLessonButton;
    private JButton editLessonButton;
    private JButton viewLessonButton;
    private JButton deleteLessonButton;
    private JPanel lessonMgmtPanel;
    private JTable lessonTable;
    private ViewLessonUI viewLessonUI;
    private CourseMgmtController courseMgmtCntrl;
    private AddLessonUI addLessonUI;
    private HomepageController homepageController;

    public LessonMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        courseMgmtCntrl.setLessonList(courseMgmtController.getSelectedCourse().getLessonList());
        courseMgmtCntrl.setLessonTable(new LessonTableModel(courseMgmtCntrl.getLessonList().getLessons()));

        this.lessonTable.setModel(courseMgmtCntrl.getLessonTable());
        lessonMgmtFrame = new JFrame("Lessons");
        this.addALLessonButtons();
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

    public JFrame getLessonMgmtFrame() {
        return lessonMgmtFrame;
    }

    public void setLessonMgmtFrame(JFrame lessonMgmtFrame) {
        this.lessonMgmtFrame = lessonMgmtFrame;
    }

    public JPanel getCrudPanel() {
        return crudPanel;
    }

    public void setCrudPanel(JPanel crudPanel) {
        this.crudPanel = crudPanel;
    }

    public JButton getAddLessonButton() {
        return addLessonButton;
    }

    public void setAddLessonButton(JButton addLessonButton) {
        this.addLessonButton = addLessonButton;
    }

    public JButton getEditLessonButton() {
        return editLessonButton;
    }

    public void setEditLessonButton(JButton editLessonButton) {
        this.editLessonButton = editLessonButton;
    }

    public JButton getViewLessonButton() {
        return viewLessonButton;
    }

    public void setViewLessonButton(JButton viewLessonButton) {
        this.viewLessonButton = viewLessonButton;
    }

    public JButton getDeleteLessonButton() {
        return deleteLessonButton;
    }

    public void setDeleteLessonButton(JButton deleteLessonButton) {
        this.deleteLessonButton = deleteLessonButton;
    }

    public JPanel getLessonMgmtPanel() {
        return lessonMgmtPanel;
    }

    public void setLessonMgmtPanel(JPanel lessonMgmtPanel) {
        this.lessonMgmtPanel = lessonMgmtPanel;
    }

    public JTable getLessonTable() {
        return lessonTable;
    }

    public void setLessonTable(JTable lessonTable) {
        this.lessonTable = lessonTable;
    }

    public ViewLessonUI getViewLessonUI() {
        return viewLessonUI;
    }

    public void setViewLessonUI(ViewLessonUI viewLessonUI) {
        this.viewLessonUI = viewLessonUI;
    }
}
