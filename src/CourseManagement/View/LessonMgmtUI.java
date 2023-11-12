package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LessonMgmtUI implements ActionListener {
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
        //lessonTable.setModel(courseMgmtCntrl.getLessonTable());
        lessonMgmtFrame = new JFrame("Lessons");
    }

    public void addALLessonButtons() {
        this.getViewLessonButton().addActionListener(this);
        this.getAddLessonButton().addActionListener(this);
        this.getEditLessonButton().addActionListener(this);
        this.getDeleteLessonButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == this.getViewLessonButton())
        {
            if(this.getViewLessonUI() != null)
            {
                this.setViewLessonUI(new ViewLessonUI());
                this.homepageController.getHomepageUI().getViewPanel().add(this.viewLessonUI.getLessonPanel(), "View Lesson");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Lesson");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }

        if (obj == this.getAddLessonButton())
        {
            this.addLessonUI = new AddLessonUI(this.courseMgmtCntrl);
            this.addLessonUI.addALNewLessonButtons();
            this.homepageController.getHomepageUI().getHomeFrame().setEnabled(false);
        }


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
