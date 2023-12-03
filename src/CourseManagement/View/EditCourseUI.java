package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import CourseManagement.Model.CourseList;
import UserAuthentication.Model.Instructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EditCourseUI {
    private JFrame editCourseFrame;
    private JPanel editCoursePanel, courseInformationPanel, buttonPanel;
    private JLabel courseIDLabel, courseNameLabel, maxEnrolledLabel, instructorLabel;
    private JTextField courseIDTextField, courseNameTextField, maxEnrolledTextField;
    private JComboBox instructorComboBox;
    private JButton cancelButton, saveButton;
    private CourseMgmtController courseMgmtController;
    private CourseMgmtUI courseMgmtUI;
    private ArrayList<Instructor> instructorList;
    private Course currentCourse;
    private CourseList courseList;
    private int courseNumber;

    public EditCourseUI(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;

        editCourseFrame = new JFrame("Edit Course");
        editCourseFrame.setResizable(false);
        editCourseFrame.setMinimumSize(new Dimension(800, 600));
        editCourseFrame.setContentPane(editCoursePanel);
        editCourseFrame.setLocationRelativeTo(null);
        editCourseFrame.setVisible(true);
        editCourseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        courseNumber = this.courseMgmtController.getCourseMgmtUI().getSelectedRow();
        loadInstructorComboBox(this.courseMgmtController.getDatabase().getAllInstructorsForSelection());
        setCourse(courseNumber);
        setCourseText();
        addALEditCourseButtons();
        addFocusListeners();
    }

    public void setCourse (int courseListNumber) {
        courseNumber = courseListNumber;
        courseList = courseMgmtController.getCourseList();
        currentCourse = courseList.getCourses().get(courseNumber);
    }

    public void setCourseText() {
        courseIDTextField.setText(currentCourse.getCourseID());
        courseNameTextField.setText(currentCourse.getCourseName());
        instructorComboBox.setSelectedItem(currentCourse.getInstructor().getFirstName() + " " + currentCourse.getInstructor().getLastName());
        maxEnrolledTextField.setText(currentCourse.getMaxEnrolled());
    }

    public void addALEditCourseButtons() {
        this.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkStringToInt())
                {
                    currentCourse.setCourseID(courseIDTextField.getText());

                    currentCourse.setCourseName(courseNameTextField.getText());
                    currentCourse.setMaxEnrolled(maxEnrolledTextField.getText());
                    currentCourse.setInstructor(selectInstructorFromList());

                    courseMgmtController.getDatabase().editCourseInDatabase(currentCourse);
                    courseMgmtController.getCourseTable().fireTableDataChanged();

                    courseMgmtController.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                    editCourseFrame.dispose();
                    courseMgmtController.setEditCourseUI(null);
                }
            }
        });
        this.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtController.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                editCourseFrame.dispose();
                courseMgmtController.setEditCourseUI(null);
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
        editCourseFrame.addWindowListener(new WindowAdapter() {
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

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public CourseMgmtController getCourseMgmtController() {
        return courseMgmtController;
    }

    public void setCourseMgmtController(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
    }
}
