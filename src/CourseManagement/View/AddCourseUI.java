package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import UserManagement.Model.Instructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddCourseUI {
    private JFrame addCourseFrame;
    private JPanel addCoursePanel;
    private JPanel buttonPanel;
    private JPanel courseInformationPanel;
    private JTextField courseIDTextField, courseNameTextField, maxEnrolledTextField;
    private JButton saveButton, cancelButton;
    private JLabel instructorLabel, maxEnrolledLabel, courseNameLabel, courseIDLabel;
    private JComboBox instructorComboBox;
    private CourseMgmtController courseMgmtCntrl;

    private ArrayList<Instructor> instructorList;

    public AddCourseUI(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
        addCourseFrame = new JFrame ("Add Course");
        addCourseFrame.setResizable(false);
        addCourseFrame.setMinimumSize(new Dimension(800, 600));
        addCourseFrame.setContentPane(addCoursePanel);
        loadInstructorComboBox(this.courseMgmtCntrl.getDatabase().getAllInstructorsForSelection());
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
                String courseID = courseIDTextField.getText();
                String courseName = courseNameTextField.getText();
                String maxEnrolled = maxEnrolledTextField.getText();
                Instructor instructor = selectInstructorFromList();

                courseMgmtCntrl.setNewCourse(new Course(courseID, courseName, maxEnrolled, instructor));
                /**
                 * Instructor for test purposes only
                 */

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

    private void loadInstructorComboBox(ArrayList<Instructor> instructorList){
        this.instructorList = instructorList;
        for (Instructor instructor : this.instructorList)
        {
            instructorComboBox.addItem(instructor.getFirstName() + " " + instructor.getLastName());
        }
    }

    private Instructor selectInstructorFromList()
    {
        return instructorList.get(instructorComboBox.getSelectedIndex());
    }

    private Instructor testInstructor() {
        int testUserID = 1001;
        String testFirstName = "Urjaswala";
        String testLastName = "Vora";
        String testLogin = "Instructor1";
        String testPassword = "Password1";
        String testRoleID = "ID1";

        Instructor testInst = new Instructor(testUserID, testLogin, testFirstName, testLastName, testPassword, testRoleID);

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
        courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
        courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().transferFocus();
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

    public JComboBox getInstructorComboBox() {
        return instructorComboBox;
    }

    public void setInstructorComboBox(JComboBox instructorComboBox) {
        this.instructorComboBox = instructorComboBox;
    }
}
