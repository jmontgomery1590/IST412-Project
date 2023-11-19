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
    public JPanel navigationPanel;
    public JPanel controlPanel;
    public JTable courseTable;
    private JPanel courseMgmtPanel;
    private JPanel crudPanel;
    public JButton addCourseButton, editCourseButton, deleteCourseButton, viewCourseButton;
    private CourseMgmtController courseMgmtCntrl;


    public CourseMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;

        // load the table data (pulled from CourseList, put into CourseTableModel, then
        // loaded into this forms table
        courseTable.setModel(courseMgmtCntrl.getCourseTable());
        courseFrame = new JFrame("Create Course");
        this.addALCourseButtons();
    }

    public void addALCourseButtons() {
        this.getViewCourseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = courseTable.getSelectedRow();
                courseMgmtCntrl.setSelectedCourse(courseMgmtCntrl.getCourseList().getCourses().get(selectedRow));
                courseMgmtCntrl.setPageMgmtUI(new PageMgmtUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().add(courseMgmtCntrl.getPageMgmtUI().getPageMgmtPanel(), "View Course");
                courseMgmtCntrl.getHomepageController().getHomepageUI().getCardSwapper().show(courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel(), "View Course");
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
        this.getAddCourseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setAddCourseUI(new AddCourseUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
            }
        });
        this.getEditCourseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.getDeleteCourseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JFrame getCourseFrame() {
        return courseFrame;
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