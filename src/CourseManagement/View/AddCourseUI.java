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

public class AddCourseUI implements ActionListener {
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
        addCourseFrame.setVisible(true);
        addCourseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFocusListeners();
    }

    public void addALNewCourseButtons() {
        this.getSaveButton().addActionListener(this.courseMgmtCntrl);
        this.getCancelButton().addActionListener(this.courseMgmtCntrl);
    }

    private Instructor testInstructor() {
        String testLogin = "Instructor1";
        String testPassword = "Password1";
        String testRoleID = "ID1";

        Instructor testInst = new Instructor(testLogin, testPassword, testRoleID);

        return testInst;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();


        // Add Course Buttons
        if (obj == this.getCancelButton())
        {
            this.homepageController.getHomepageUI().getHomeFrame().setEnabled(true);
            this.getAddCourseFrame().dispose();
        }
        else if (obj == this.getSaveButton())
        {
            newCourse.setCourseID(getCourseIDTextField().getText());
            newCourse.setCourseName(getCourseNameTextField().getText());
            newCourse.setMaxEnrolled(getMaxEnrolledTextField().getText());
            /**
             * Instructor for test purposes only
             */
            newCourse.setInstructor(testInstructor());

            this.courseList.getCourses().add(newCourse);
            this.homepageController.getHomepageUI().getHomeFrame().setEnabled(true);
            this.getAddCourseFrame().dispose();
        }
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

    public void setAddCourseFrame(JFrame addCourseFrame) {
        this.addCourseFrame = addCourseFrame;
    }

    public JTextField getCourseNameTextField() {
        return courseNameTextField;
    }

    public void setCourseNameTextField(JTextField courseNameTextField) {
        this.courseNameTextField = courseNameTextField;
    }

    public JTextField getCourseIDTextField() {
        return courseIDTextField;
    }

    public void setCourseIDTextField(JTextField courseIDTextField) {
        this.courseIDTextField = courseIDTextField;
    }

    public JTextField getMaxEnrolledTextField() {
        return maxEnrolledTextField;
    }

    public void setMaxEnrolledTextField(JTextField maxEnrolledTextField) {
        this.maxEnrolledTextField = maxEnrolledTextField;
    }

    public JTextField getInstructorTextField() {
        return instructorTextField;
    }

    public void setInstructorTextField(JTextField instructorTextField) {
        this.instructorTextField = instructorTextField;
    }

    public JPanel getAddCoursePanel() {
        return addCoursePanel;
    }

    public void setAddCoursePanel(JPanel addCoursePanel) {
        this.addCoursePanel = addCoursePanel;
    }

    public JPanel getCourseInformationPanel() {
        return courseInformationPanel;
    }

    public void setCourseInformationPanel(JPanel courseInformationPanel) {
        this.courseInformationPanel = courseInformationPanel;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JLabel getInstructorLabel() {
        return instructorLabel;
    }

    public void setInstructorLabel(JLabel instructorLabel) {
        this.instructorLabel = instructorLabel;
    }

    public JLabel getMaxEnrolledLabel() {
        return maxEnrolledLabel;
    }

    public void setMaxEnrolledLabel(JLabel maxEnrolledLabel) {
        this.maxEnrolledLabel = maxEnrolledLabel;
    }

    public JLabel getCourseNameLabel() {
        return courseNameLabel;
    }

    public void setCourseNameLabel(JLabel courseNameLabel) {
        this.courseNameLabel = courseNameLabel;
    }

    public JLabel getCourseIDLabel() {
        return courseIDLabel;
    }

    public void setCourseIDLabel(JLabel courseIDLabel) {
        this.courseIDLabel = courseIDLabel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}
