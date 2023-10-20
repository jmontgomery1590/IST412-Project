package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.CourseTableModel;
import CourseManagement.Model.Course;

import javax.swing.*;
import java.util.Scanner;

public class CourseMgmtInterface {
    public CourseTableModel tableModel;
    public JFrame courseFrame;
    public JPanel navigationPanel, controlPanel;
    public JTable courseTable;
    private JPanel courseMgmtPanel;
    private JButton addCourseButton, editCourseButton, deleteCourseButton, viewCourseButton;
    private JPanel crudPanel;
    private CourseMgmtController courseMgmtCntrl;


    public CourseMgmtInterface(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;

        // load the table data (pulled from CourseList, put into CourseTableModel, then
        // loaded into this forms table
        courseTable.setModel(courseMgmtCntrl.getCourseTable());
    }

    /**
     * Displays the chosen course interface
     */
    public Course createCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCourse ID#:");
        String courseID = scanner.nextLine();
        System.out.println("Course Name:");
        String courseName = scanner.nextLine();
        System.out.println("Max Enrolled:");
        int max = 0;
        while (scanner.hasNext()){
            if (!scanner.hasNextInt()){
                System.out.println("Please input a valid integer.");
                scanner.next();
            } else {
                max = scanner.nextInt();
                break;
            }
        }
        System.out.println("\n---Course Created Successfully---\n");
        return new Course(courseID, courseName, max);
    }

    public CourseTableModel getTableModel() {
        return tableModel;
    }

    public JFrame getCourseFrame() {
        return courseFrame;
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
