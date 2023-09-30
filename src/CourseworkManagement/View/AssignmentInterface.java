package CourseworkManagement.View;

import CourseworkManagement.Model.Assignment;

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
    public void displayAssignments(Assignment a1) {}
}
