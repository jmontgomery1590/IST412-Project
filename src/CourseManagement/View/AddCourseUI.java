package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import CourseManagement.Model.CourseList;
import StaffManagement.Model.Instructor;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddCourseUI {
    private JFrame addCourseFrame;
    private JTextField courseIDTextField;
    private JTextField courseNameTextField;
    private JTextField maxEnrolledTextField;
    private JTextField instructorTextField;
    private JPanel addCoursePanel;
    private JPanel courseInformationPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel instructorLabel;
    private JLabel maxEnrolledLabel;
    private JLabel courseNameLabel;
    private JLabel courseIDLabel;
    private JPanel buttonPanel;
    private HomepageController homepageController;
    private Course newCourse;
    private CourseMgmtController courseMgmtCntrl;
    private CourseList courseList;

    public AddCourseUI(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
        addCourseFrame = new JFrame ("Add Course");
        addCourseFrame.setResizable(false);
        addCourseFrame.setMinimumSize(new Dimension(800, 600));
        addCourseFrame.setContentPane(addCoursePanel);
        addCourseFrame.setLocationRelativeTo(null);
        addALNewCourseButtons();
        addCourseFrame.setVisible(true);
        addCourseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFocusListeners();
    }

    public void addALNewCourseButtons() {
        this.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.getNewCourse().setCourseID(courseIDTextField.getText());
                courseMgmtCntrl.getNewCourse().setCourseName(courseNameTextField.getText());
                courseMgmtCntrl.getNewCourse().setMaxEnrolled(maxEnrolledTextField.getText());
                /**
                 * Instructor for test purposes only
                 */
                courseMgmtCntrl.getNewCourse().setInstructor(testInstructor());

                courseMgmtCntrl.getCourseList().getCourses().add(courseMgmtCntrl.getNewCourse());
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                courseMgmtCntrl.getCourseTable().fireTableDataChanged();
                addCourseFrame.dispose();
            }
        });
        this.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                addCourseFrame.dispose();
                courseMgmtCntrl.setAddCourseUI(null);
            }
        });
    }

    private Instructor testInstructor() {
        String testLogin = "Instructor1";
        String testPassword = "Password1";
        String testRoleID = "ID1";

        Instructor testInst = new Instructor(testLogin, testPassword, testRoleID);

        return testInst;
    }

    private void addFocusListeners() {
        addCourseFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });
    }

    private void onWindowClosing() {
        courseMgmtCntrl.getCourseMgmtUI().getCourseFrame().setEnabled(true);
        courseMgmtCntrl.getCourseMgmtUI().getCourseFrame().transferFocus();
    }

    public JFrame getAddCourseFrame() {
        return addCourseFrame;
    }

    public JTextField getCourseNameTextField() {
        return courseNameTextField;
    }

    public JTextField getCourseIDTextField() {
        return courseIDTextField;
    }

    public JTextField getMaxEnrolledTextField() {
        return maxEnrolledTextField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}
