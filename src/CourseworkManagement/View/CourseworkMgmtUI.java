package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;

import javax.swing.*;
import java.awt.event.*;

public class CourseworkMgmtUI extends JFrame{
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
    private JButton courseAssignmentButton;
    private JButton studentAssignmentsButton;
    private CourseworkMgmtController courseworkMgmtCntrl;
    private JFrame view;

    public CourseworkMgmtUI(CourseworkMgmtController courseworkMgmtCntrl) {
        view = new JFrame();
        this.setCourseworkMgmtCntrl(courseworkMgmtCntrl);
        this.courseAssignmentLabel.setText(courseworkMgmtCntrl.getCurrentCourse().getCourseID() + " " + courseworkMgmtCntrl.getCurrentCourse().getCourseName());
        loadAssignmentTable();
        //assignmentTable.setModel(this.courseworkMgmtCntrl.getAssignmentTable());
        view.add(courseworkPanel);
        this.addALButtons();
        this.verifyButtonAccess();
        this.addFocusListeners();
    }

    private void verifyButtonAccess() {
        if (courseworkMgmtCntrl.getCurrentUser().getRoleID().equals("4"))
        {
            newAssignmentButton.setVisible(false);
            deleteAssignmentButton.setVisible(false);
            editAssignmentButton.setVisible(false);
        }
    }

    private void addFocusListeners(){
        view.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                super.windowGainedFocus(e);

                courseworkMgmtCntrl.getStudentAssignmentTableModel().fireTableDataChanged();
            }
        });
    }

    private void loadAssignmentTable()
    {
        if (courseworkMgmtCntrl.getCurrentUser().getRoleID().equals("4"))
        {
            assignmentTable.setModel(this.courseworkMgmtCntrl.getStudentAssignmentTableModel());
        }
        else
        {
            assignmentTable.setModel(this.courseworkMgmtCntrl.getCourseAssignmentTableModel());
        }
    }

    private void addALButtons(){
        this.viewAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = assignmentTable.getSelectedRow();
                courseworkMgmtCntrl.setAssignment(courseworkMgmtCntrl.getAssignmentList().getAssignments().get(selectedRow));
                courseworkMgmtCntrl.setViewAssignmentUI(new ViewAssignmentUI(courseworkMgmtCntrl));

                courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().getViewPanel().add(courseworkMgmtCntrl.getViewAssignmentUI().getReadPanel(), "Load Assignment");
                courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().getCardSwapper().show(courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().getViewPanel(), "Load Assignment");
                courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
        this.editAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.newAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtCntrl.setAssignmentInterface(new AddAssignmentUI(courseworkMgmtCntrl));
                courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
            }
        });
        this.deleteAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.courseAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtCntrl.loadCourseAssignmentList();
                assignmentTable.setModel(courseworkMgmtCntrl.getCourseAssignmentTableModel());
            }
        });

        this.studentAssignmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtCntrl.loadAllStudentsAssignmentList();
                assignmentTable.setModel(courseworkMgmtCntrl.getAssignmentByStudentTablemodel());
            }
        });
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