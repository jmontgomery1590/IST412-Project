package CourseworkManagement.Model;

import CourseManagement.Model.Course;
import UserAuthentication.Model.Student;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AssignmentByStudentTablemodel extends AbstractTableModel {
    String columnNames[] = {"Student", "Assignment", "Earned Score", "Possible Score", "Grade"};
    private ArrayList<Assignment> assignmentList;

    public AssignmentByStudentTablemodel(ArrayList<Assignment> newAssignmentList) {
        assignmentList = newAssignmentList;
    }

    @Override
    public int getRowCount() { return assignmentList.size(); }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> assignmentList.get(rowIndex).getAssignedStudent().getFirstName() + " " + assignmentList.get(rowIndex).getAssignedStudent().getLastName();
            case 1 -> assignmentList.get(rowIndex).getAssignmentTitle();
            case 2 -> assignmentList.get(rowIndex).getEarnedScore();
            case 3 -> assignmentList.get(rowIndex).getPossibleScore();
            case 4 -> assignmentList.get(rowIndex).getGrade();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int col) { return columnNames[col] ;}
}
