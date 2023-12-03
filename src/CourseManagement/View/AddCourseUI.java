package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import UserAuthentication.Model.Instructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AddCourseUI {
    private JFrame addCourseFrame;
    private JPanel addCoursePanel, buttonPanel, courseInformationPanel;
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
        addCourseFrame.setLocationRelativeTo(null);
        addCourseFrame.setVisible(true);
        addCourseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loadInstructorComboBox(this.courseMgmtCntrl.getDatabase().getAllInstructorsForSelection());
        addALNewCourseButtons();
        addFocusListeners();
    }

    public void addALNewCourseButtons() {
        this.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkStringToInt())
                {
                    String courseID = courseIDTextField.getText();
                    String courseName = courseNameTextField.getText();
                    String maxEnrolled = maxEnrolledTextField.getText();
                    Instructor instructor = selectInstructorFromList();
                    courseMgmtCntrl.setNewCourse(new Course(0, courseID, courseName, maxEnrolled, instructor));

                    courseMgmtCntrl.getCourseList().getCourses().add(courseMgmtCntrl.getNewCourse());
                    courseMgmtCntrl.getDatabase().addCourseToDatabase(courseMgmtCntrl);

                    courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                    courseMgmtCntrl.getCourseTable().fireTableDataChanged();
                    addCourseFrame.dispose();
                }
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

    private boolean checkStringToInt(){
        String maxEnrolled = maxEnrolledTextField.getText();
        int maxEnrolledConverted = 0;
        try
        {
            maxEnrolledConverted = Integer.parseInt(maxEnrolled);
        } catch (Exception e)
        {
            maxEnrolledTextField.setText("Please enter a valid number!");
            maxEnrolledLabel.setForeground(Color.red);
            return false;
        }
        return true;
    }

    private Instructor selectInstructorFromList()
    {
        return instructorList.get(instructorComboBox.getSelectedIndex());
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
}
