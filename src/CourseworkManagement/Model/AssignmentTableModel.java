package CourseworkManagement.Model;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AssignmentTableModel extends AbstractTableModel {
    String columnNames[] = {"Assignment", "Earned Score", "Possible Score", "Grade"};
    private ArrayList<Assignment> assignmentList;

    public AssignmentTableModel(ArrayList<Assignment> newAssignmentList) { assignmentList = newAssignmentList; }

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
            case 3 -> assignmentList.get(rowIndex).getGrade();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int col) { return columnNames[col] ;}
}
