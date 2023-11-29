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

public class EditCourseUI {
    private JFrame editCourseFrame;
    private JPanel editCoursePanel, courseInformationPanel;
    private JLabel courseIDLabel, courseNameLabel, maxEnrolledLabel, instructorLabel;
    private JTextField courseIDTextField, courseNameTextField, maxEnrolledTextField;
    private JComboBox instructorComboBox;
    private JPanel buttonPanel;
    private JButton cancelButton, saveButton;
    private CourseMgmtController courseMgmtController;
    private CourseMgmtUI courseMgmtUI;
    private ArrayList<Instructor> instructorList;
    private Course selectedCourse;

    public EditCourseUI(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
        //selectedCourse = courseMgmtController.getSelectedCourse();
        editCourseFrame = new JFrame("Edit Course");
        editCourseFrame.setResizable(false);
        editCourseFrame.setMinimumSize(new Dimension(800, 600));
        editCourseFrame.setContentPane(editCoursePanel);
        editCourseFrame.setLocationRelativeTo(null);
        editCourseFrame.setVisible(true);
        editCourseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //setCourseText();
        loadInstructorComboBox(this.courseMgmtController.getDatabase().getAllInstructorsForSelection());
        addALEditCourseButtons();
        addFocusListeners();
    }

    public void setCourseText() {
        courseIDTextField.setText(selectedCourse.getCourseID());
        courseNameTextField.setText(selectedCourse.getCourseName());
        instructorComboBox.setSelectedItem(selectedCourse.getInstructor());
        maxEnrolledTextField.setText(selectedCourse.getMaxEnrolled());
    }

    public void addALEditCourseButtons() {
        this.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseID = courseIDTextField.getText();
                String courseName = courseNameTextField.getText();
                String maxEnrolled = maxEnrolledTextField.getText();
                Instructor instructor = selectInstructorFromList();

                /*courseMgmtController.setSelectedCourse(new Course(0, courseID, courseName, maxEnrolled, instructor));

                courseMgmtController.getCourseList().getCourses().add(courseMgmtCntrl.getSelectedCourse());
                courseMgmtController.getDatabase().addCourseToDatabase(courseMgmtCntrl);
                courseMgmtController.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                courseMgmtController.getCourseTable().fireTableDataChanged();
                editCourseFrame.dispose();*/
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

    public JFrame getEditCourseFrame() {
        return editCourseFrame;
    }

    public void setEditCourseFrame(JFrame editCourseFrame) {
        this.editCourseFrame = editCourseFrame;
    }

    public JPanel getEditCoursePanel() {
        return editCoursePanel;
    }

    public void setEditCoursePanel(JPanel editCoursePanel) {
        this.editCoursePanel = editCoursePanel;
    }

    public JPanel getCourseInformationPanel() {
        return courseInformationPanel;
    }

    public void setCourseInformationPanel(JPanel courseInformationPanel) {
        this.courseInformationPanel = courseInformationPanel;
    }

    public JLabel getCourseIDLabel() {
        return courseIDLabel;
    }

    public void setCourseIDLabel(JLabel courseIDLabel) {
        this.courseIDLabel = courseIDLabel;
    }

    public JLabel getCourseNameLabel() {
        return courseNameLabel;
    }

    public void setCourseNameLabel(JLabel courseNameLabel) {
        this.courseNameLabel = courseNameLabel;
    }

    public JLabel getMaxEnrolledLabel() {
        return maxEnrolledLabel;
    }

    public void setMaxEnrolledLabel(JLabel maxEnrolledLabel) {
        this.maxEnrolledLabel = maxEnrolledLabel;
    }

    public JLabel getInstructorLabel() {
        return instructorLabel;
    }

    public void setInstructorLabel(JLabel instructorLabel) {
        this.instructorLabel = instructorLabel;
    }

    public JTextField getCourseIDTextField() {
        return courseIDTextField;
    }

    public void setCourseIDTextField(JTextField courseIDTextField) {
        this.courseIDTextField = courseIDTextField;
    }

    public JTextField getCourseNameTextField() {
        return courseNameTextField;
    }

    public void setCourseNameTextField(JTextField courseNameTextField) {
        this.courseNameTextField = courseNameTextField;
    }

    public JTextField getMaxEnrolledTextField() {
        return maxEnrolledTextField;
    }

    public void setMaxEnrolledTextField(JTextField maxEnrolledTextField) {
        this.maxEnrolledTextField = maxEnrolledTextField;
    }

    public JComboBox getInstructorComboBox() {
        return instructorComboBox;
    }

    public void setInstructorComboBox(JComboBox instructorComboBox) {
        this.instructorComboBox = instructorComboBox;
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

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
    }

    public ArrayList<Instructor> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(ArrayList<Instructor> instructorList) {
        this.instructorList = instructorList;
    }
}
