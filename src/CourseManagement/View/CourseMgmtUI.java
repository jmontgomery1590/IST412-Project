package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.CourseTableModel;
import CourseManagement.Model.Course;

import javax.swing.*;
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


    public CourseMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;

        // load the table data (pulled from CourseList, put into CourseTableModel, then
        // loaded into this forms table
        courseTable.setModel(courseMgmtCntrl.getCourseTable());
        initComponents();
        this.addALCourseButtons();
        this.addFocusListeners();
    }

    public void addALCourseButtons() {
        this.getViewCourseButton().addActionListener(this.courseMgmtCntrl);
        this.getAddCourseButton().addActionListener(this.courseMgmtCntrl);
        this.getEditCourseButton().addActionListener(this.courseMgmtCntrl);
        this.getDeleteCourseButton().addActionListener(this.courseMgmtCntrl);
    }

    public void initComponents() {
        courseFrame = new JFrame("Create Course");
    }

    private void addFocusListeners() {
        courseFrame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                super.windowGainedFocus(e);

                courseMgmtCntrl.getCourseTable().fireTableDataChanged();
            }
        });
    }

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
}
