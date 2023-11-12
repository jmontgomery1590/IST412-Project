package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.CourseTableModel;
import CourseManagement.Model.Course;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class CourseMgmtUI {
    public CourseTableModel tableModel;
    public JFrame courseFrame;
    public JPanel navigationPanel, controlPanel;
    public JTable courseTable;
    private JPanel courseMgmtPanel;
    public JButton addCourseButton, editCourseButton, deleteCourseButton, viewCourseButton;
    private JPanel crudPanel;
    private CourseMgmtController courseMgmtCntrl;
    private PageMgmtUI pageMgmtUI;
    private HomepageController homepageController;


    public CourseMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;

        // load the table data (pulled from CourseList, put into CourseTableModel, then
        // loaded into this forms table
        courseTable.setModel(courseMgmtCntrl.getCourseTable());
        courseFrame = new JFrame("Create Course");
        this.addALCourseButtons();
    }

    public void addALCourseButtons() {
        this.getViewCourseButton().addActionListener(this.courseMgmtCntrl);
        this.getAddCourseButton().addActionListener(this.courseMgmtCntrl);
        this.getEditCourseButton().addActionListener(this.courseMgmtCntrl);
        this.getDeleteCourseButton().addActionListener(this.courseMgmtCntrl);
    }

    /*@Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == this.getViewCourseButton()) {
            if (this.getPageMgmtUI() != null) {
                this.setPageMgmtUI(new PageMgmtUI(this.courseMgmtCntrl));
                this.pageMgmtUI.addALPageButtons();
                this.homepageController.getHomepageUI().getViewPanel().add(this.pageMgmtUI.getPageMgmtPanel(), "View Course");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Course");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }

        if (obj == this.getAddCourseButton()) {
            AddCourseUI addCourseUI = new AddCourseUI(this.courseMgmtCntrl);
            addCourseUI.addALNewCourseButtons();
            this.homepageController.getHomepageUI().getHomeFrame().setEnabled(false);
        }
    }*/


    public JFrame getCourseFrame() {
        return courseFrame;
    }

    public CourseTableModel getTableModel() {
        return tableModel;
    }

    public JPanel getNavigationPanel() {
        return navigationPanel;
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }

    public JTable getCourseTable() {
        return courseTable;
    }

    public JPanel getCourseMgmtPanel() {
        return courseMgmtPanel;
    }

    public JButton getAddCourseButton() {
        return addCourseButton;
    }

    public JButton getEditCourseButton() {
        return editCourseButton;
    }

    public JButton getDeleteCourseButton() {
        return deleteCourseButton;
    }

    public JButton getViewCourseButton() {
        return viewCourseButton;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public PageMgmtUI getPageMgmtUI() {
        return pageMgmtUI;
    }

    public void setPageMgmtUI(PageMgmtUI pageMgmtUI) {
        this.pageMgmtUI = pageMgmtUI;
    }
}
