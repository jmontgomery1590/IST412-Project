package CourseworkManagement.Model;

import java.sql.Array;
import java.util.ArrayList;

public class AssignmentList {

    private Assignment assignment;
    private ArrayList<Assignment> assignments = new ArrayList<>();

    public AssignmentList(){}

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }
}
