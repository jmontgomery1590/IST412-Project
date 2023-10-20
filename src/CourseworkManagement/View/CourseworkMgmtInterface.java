package CourseworkManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;

import javax.swing.*;
import java.util.Scanner;

public class CourseworkMgmtInterface {
    private JPanel course;
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

    public CourseworkMgmtInterface(CourseworkMgmtController courseworkMgmtCntrl) {
        this.setCourseworkMgmtCntrl(courseworkMgmtCntrl);
        assignmentTable.setModel(this.courseworkMgmtCntrl.getAssignmentTable());
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
}
