package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;

import javax.swing.*;
import java.awt.event.*;

public class CourseworkMgmtUI extends JFrame{
    public CourseworkMgmtUI(CourseworkMgmtController courseworkMgmtCntrl) {
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

    public void setCourseworkMgmtCntrl(CourseworkMgmtController courseworkMgmtCntrl) {
        this.courseworkMgmtCntrl = courseworkMgmtCntrl;
    }

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

    public JPanel getCourseworkPanel() {
        return courseworkPanel;
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

    public JFrame getView() {
        return view;
    }

    public void setView(JFrame view) {
        this.view = view;
    }
}
