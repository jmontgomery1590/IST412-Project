package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Assignment;

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
                Assignment currentAssignment = courseworkMgmtCntrl.getAssignmentList().getAssignments().get(assignmentTable.getSelectedRow());
                courseworkMgmtCntrl.setEditAssignmentUI(new EditAssignmentUI(courseworkMgmtCntrl, currentAssignment));

                courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
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
                Assignment currentAssignment = courseworkMgmtCntrl.getAssignmentList().getAssignments().get(assignmentTable.getSelectedRow());
                courseworkMgmtCntrl.getDatabase().disableAssignmentFromDatabase(currentAssignment);

                courseworkMgmtCntrl.getAssignmentList().getAssignments().remove(currentAssignment);
                courseworkMgmtCntrl.getCourseAssignmentTableModel().fireTableDataChanged();

                courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
            }
        });

        this.courseAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtCntrl.loadCourseAssignmentList();
                assignmentTable.setModel(courseworkMgmtCntrl.getCourseAssignmentTableModel());
                editAssignmentButton.setEnabled(true);
                deleteAssignmentButton.setEnabled(true);
            }
        });

        this.studentAssignmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtCntrl.loadAllStudentsAssignmentList();
                assignmentTable.setModel(courseworkMgmtCntrl.getAssignmentByStudentTablemodel());
                editAssignmentButton.setEnabled(false);
                deleteAssignmentButton.setEnabled(false);
            }
        });
    }

    public void setCourseworkMgmtCntrl(CourseworkMgmtController courseworkMgmtCntrl) {
        this.courseworkMgmtCntrl = courseworkMgmtCntrl;
    }

    public JPanel getCourseworkPanel() {
        return courseworkPanel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JTable getAssignmentTable() {
        return assignmentTable;
    }

    public JFrame getView() {
        return view;
    }

    public void setView(JFrame view) {
        this.view = view;
    }
}