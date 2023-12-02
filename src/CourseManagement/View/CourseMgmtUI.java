package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Announcement;
import CourseManagement.Model.Course;
import CourseManagement.Model.CourseTableModel;
import CourseManagement.Model.Lesson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseMgmtUI {
    public CourseTableModel tableModel;
    public JFrame courseFrame;
    public JPanel navigationPanel;
    public JPanel controlPanel;
    public JTable courseTable;
    private JPanel courseMgmtPanel;
    private JPanel buttonPanel;
    public JButton newCourseButton, editCourseButton, deleteCourseButton, viewCourseButton;
    private JPanel tablePanel;
    private JScrollPane tableScrollPane;
    private CourseMgmtController courseMgmtCntrl;
    private Lesson selectedLesson;
    private Announcement selectedAnnouncement;
    private Course selectedCourse;
    private int selectedRow;


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
                selectedRow = courseTable.getSelectedRow();
                courseMgmtCntrl.setSelectedCourse(courseMgmtCntrl.getCourseList().getCourses().get(selectedRow));
                courseMgmtCntrl.setPageMgmtUI(new PageMgmtUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().add(courseMgmtCntrl.getPageMgmtUI().getPageMgmtPanel(), "View Course");

                courseMgmtCntrl.getHomepageController().getHomepageUI().getCardSwapper().show(courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel(), "View Course");
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
        this.getNewCourseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setAddCourseUI(new AddCourseUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
            }
        });
        this.getEditCourseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedRow = courseTable.getSelectedRow();
                courseMgmtCntrl.setEditCourseUI(new EditCourseUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
            }
        });
        this.getDeleteCourseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public JFrame getCourseFrame() {
        return courseFrame;
    }

    public JPanel getCourseMgmtPanel() {
        return courseMgmtPanel;
    }

    public JButton getNewCourseButton() {
        return newCourseButton;
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

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }
}
