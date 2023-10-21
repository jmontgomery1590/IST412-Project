package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;

import javax.swing.*;
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
        assignmentTable.setModel(this.courseworkMgmtCntrl.getAssignmentTable());
        view.add(courseworkPanel);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setExtendedState(MAXIMIZED_BOTH);
        view.setVisible(true);
    }


    public Assignment createNewAssignment(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the assignment title:");
        String assignmentTitle = scanner.nextLine();
        System.out.println("\n---Assignment Created Successfully---\n");
        return new Assignment(assignmentTitle);
    }


    /**
     * Displays the chosen Assignment interface
     * @param a1 Assignment to be displayed in management interface
     */
    public void displayAssignments(Assignment a1) {
        System.out.println("\nAssignment Title: " + a1.getAssignmentTitle());
        int questionNum = 1;
        for (Question question: a1.getAssignmentQuestions()) {
            System.out.println("\tQuestion #" + questionNum + ": " + question.getDesc());
            questionNum ++;
            int i = 1;
            for (Answer answer: question.getPossibleAnswers()) {
                System.out.println("\t\tOption #" + i + ": " + answer.getAnswerDescription());
                i ++;
            }
        }
        System.out.println("\n---Assignment Information Displayed Successfully---");
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
