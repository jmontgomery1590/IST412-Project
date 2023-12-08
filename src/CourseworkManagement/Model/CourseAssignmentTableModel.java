package CourseworkManagement.Model;

import CourseManagement.Model.Course;
import UserAuthentication.Model.Student;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CourseAssignmentTableModel extends AbstractTableModel {
    String columnNames[] = {"Assignment", "Total Points", "Amount of Questions"};
    private ArrayList<Assignment> assignmentList;

    public CourseAssignmentTableModel(ArrayList<Assignment> newAssignmentList) { assignmentList = newAssignmentList; }


    @Override
    public int getRowCount() { return assignmentList.size(); }

    @Override
    public int getColumnCount() { return 3; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return switch (columnIndex) {
            case 0 -> assignmentList.get(rowIndex).getAssignmentTitle();
            case 1 -> assignmentList.get(rowIndex).getPossibleScore();
            case 2 -> assignmentList.get(rowIndex).getQuestionList().getQuestionList().size();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int col) { return columnNames[col] ;}

}
