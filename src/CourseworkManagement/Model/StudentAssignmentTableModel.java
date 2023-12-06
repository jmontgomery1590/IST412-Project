package CourseworkManagement.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StudentAssignmentTableModel extends AbstractTableModel {
    String columnNames[] = {"Assignment", "Earned Score", "Possible Score", "Grade"};
    private ArrayList<Assignment> assignmentList;

    public StudentAssignmentTableModel(ArrayList<Assignment> newAssignmentList) { assignmentList = newAssignmentList; }

    @Override
    public int getRowCount() { return assignmentList.size(); }

    @Override
    public int getColumnCount() { return 4; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> assignmentList.get(rowIndex).getAssignmentTitle();
            case 1 -> assignmentList.get(rowIndex).getEarnedScore();
            case 2 -> assignmentList.get(rowIndex).getPossibleScore();
            case 3 -> assignmentList.get(rowIndex).getGrade() + "%";
            default -> null;
        };
    }

    @Override
    public String getColumnName(int col) { return columnNames[col] ;}
}
