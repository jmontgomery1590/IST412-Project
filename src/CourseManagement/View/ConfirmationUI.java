package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import CourseManagement.Model.CourseList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfirmationUI {
    private JFrame confirmationFrame;
    private JPanel confirmationPanel, buttonPanel, textPanel;
    private JButton yesButton, noButton;
    private JLabel confirmationLabel1;
    private JLabel confirmationLabel2;
    private JLabel confirmationLabel3;
    private CourseMgmtController courseMgmtController;
    private int courseNumber;
    private CourseList courseList;
    private Course selectedCourse;

    public ConfirmationUI(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
        confirmationFrame = new JFrame("Please Confirm");
        confirmationFrame.setResizable(false);
        confirmationFrame.setMinimumSize(new Dimension(600,400));
        confirmationFrame.setContentPane(confirmationPanel);
        confirmationFrame.setLocationRelativeTo(null);
        confirmationFrame.setVisible(true);
        confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        confirmationLabel1.setText("Caution:");
        confirmationLabel2.setText("Deleting a course will disable all associated announcements, lessons,");
        confirmationLabel3.setText("and assignments. Are you sure you wish to continue with this process?");

        addALConfirmationButtons();
        addFocusListeners();
    }

    public void addALConfirmationButtons() {
        this.getYesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = courseMgmtController.getCourseMgmtUI().getSelectedRow();
                selectedCourse = courseMgmtController.getCourseList().getCourses().get(selectedRow);
                courseMgmtController.getDatabase().disableCourseInDatabase(selectedCourse);
                courseMgmtController.getCourseList().getCourses().remove(selectedRow);
                courseMgmtController.getCourseTable().fireTableDataChanged();

                courseMgmtController.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                confirmationFrame.dispose();
            }
        });

        this.getNoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtController.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                confirmationFrame.dispose();
            }
        });
    }

    private void addFocusListeners() {
        confirmationFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });
    }

    private void onWindowClosing() {
        courseMgmtController.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
        courseMgmtController.getHomepageController().getHomepageUI().getHomeFrame().transferFocus();
    }

    public JButton getYesButton() {
        return yesButton;
    }

    public JButton getNoButton() {
        return noButton;
    }
}
