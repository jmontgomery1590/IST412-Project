package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;

import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

public class CourseworkMgmtInterface extends JFrame{
    private JPanel courseworkPanel;
    private JLabel courseAssignmentLabel;
    private JPanel buttonPanel;
    private JButton newAssignmentButton;
    private JButton editAssignmentButton;
    private JButton deleteAssignmentButton;
    private JPanel tablePanel;
    private JPanel courseworkTitlePanel;
    private JScrollPane tableScrollPane;
    private JTable assignmentTable;
    private JButton viewAssignmentButton;
    private CourseworkMgmtController courseworkMgmtCntrl;
    private JFrame view;

    public CourseworkMgmtInterface(CourseworkMgmtController courseworkMgmtCntrl) {
        view = new JFrame();
        this.setCourseworkMgmtCntrl(courseworkMgmtCntrl);
        this.courseAssignmentLabel.setText(courseworkMgmtCntrl.getCurrentCourse().getCourseID() + " " + courseworkMgmtCntrl.getCurrentCourse().getCourseName());
        assignmentTable.setModel(this.courseworkMgmtCntrl.getAssignmentTable());
        view.add(courseworkPanel);
        this.addALButtons();
        this.addFocusListeners();
    }

    private void addFocusListeners(){
        view.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                super.windowGainedFocus(e);

                courseworkMgmtCntrl.getAssignmentTable().fireTableDataChanged();
            }
        });
    }

    private void addALButtons(){
        this.viewAssignmentButton.addActionListener(this.courseworkMgmtCntrl);
        this.editAssignmentButton.addActionListener(this.courseworkMgmtCntrl);
        this.newAssignmentButton.addActionListener(this.courseworkMgmtCntrl);
        this.deleteAssignmentButton.addActionListener(this.courseworkMgmtCntrl);
    }

    public CourseworkMgmtController getCourseworkMgmtCntrl() {
        return courseworkMgmtCntrl;
    }

    public void setCourseworkMgmtCntrl(CourseworkMgmtController courseworkMgmtCntrl) {
        this.courseworkMgmtCntrl = courseworkMgmtCntrl;
    }

    public JPanel getCourseworkPanel() {
        return courseworkPanel;
    }

    public void setCourseworkPanel(JPanel courseworkPanel) {
        this.courseworkPanel = courseworkPanel;
    }

    public JLabel getCourseAssignmentLabel() {
        return courseAssignmentLabel;
    }

    public void setCourseAssignmentLabel(JLabel courseAssignmentLabel) {
        this.courseAssignmentLabel = courseAssignmentLabel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JButton getNewAssignmentButton() {
        return newAssignmentButton;
    }

    public void setNewAssignmentButton(JButton newAssignmentButton) {
        this.newAssignmentButton = newAssignmentButton;
    }

    public JButton getEditAssignmentButton() {
        return editAssignmentButton;
    }

    public void setEditAssignmentButton(JButton editAssignmentButton) {
        this.editAssignmentButton = editAssignmentButton;
    }

    public JButton getDeleteAssignmentButton() {
        return deleteAssignmentButton;
    }

    public void setDeleteAssignmentButton(JButton deleteAssignmentButton) {
        this.deleteAssignmentButton = deleteAssignmentButton;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(JPanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    public JPanel getCourseworkTitlePanel() {
        return courseworkTitlePanel;
    }

    public void setCourseworkTitlePanel(JPanel courseworkTitlePanel) {
        this.courseworkTitlePanel = courseworkTitlePanel;
    }

    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }

    public void setTableScrollPane(JScrollPane tableScrollPane) {
        this.tableScrollPane = tableScrollPane;
    }

    public JTable getAssignmentTable() {
        return assignmentTable;
    }

    public void setAssignmentTable(JTable assignmentTable) {
        this.assignmentTable = assignmentTable;
    }

    public JButton getViewAssignmentButton() {
        return viewAssignmentButton;
    }

    public void setViewAssignmentButton(JButton viewAssignmentButton) {
        this.viewAssignmentButton = viewAssignmentButton;
    }

    public JFrame getView() {
        return view;
    }

    public void setView(JFrame view) {
        this.view = view;
    }
}
