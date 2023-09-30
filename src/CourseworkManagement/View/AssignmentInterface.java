package CourseworkManagement.View;

import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;

import java.util.Scanner;

public class AssignmentInterface {
    public AssignmentInterface() {}


    public Assignment createNewAssignment(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the assignment title:");
        String assignmentTitle = scanner.nextLine();
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
    }
}
