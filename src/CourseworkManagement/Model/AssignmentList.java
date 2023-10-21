package CourseworkManagement.Model;

import java.sql.Array;
import java.util.ArrayList;

public class AssignmentList {

    private Assignment assignment;
    private ArrayList<Assignment> assignments = new ArrayList<>();

    public AssignmentList(){
        if (this.getAssignments().isEmpty()){
            createAssignmentsList();
        }
    }

    public void createAssignmentsList(){
        for (int i = 1; i <= 6; i++){
            this.setAssignment(new Assignment("Assignment #" + (i)));
            this.getAssignment().setEarnedScore(i * 15);
            this.getAssignment().setPossibleScore((i + 1) * 15);
            this.getAssignment().gradeAssignment();
            this.getAssignments().add(this.getAssignment());
        }
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }
}
