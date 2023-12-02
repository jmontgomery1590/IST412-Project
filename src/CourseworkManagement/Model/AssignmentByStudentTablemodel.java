package CourseworkManagement.Model;

import CourseManagement.Model.Course;
import UserAuthentication.Model.Student;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AssignmentByStudentTablemodel extends AbstractTableModel {
    String columnNames[] = {"Student", "Assignment", "Earned Score", "Possible Score", "Grade"};
    private ArrayList<Assignment> assignmentList;
    private Course course;

    public AssignmentByStudentTablemodel(ArrayList<Assignment> newAssignmentList, Course newCourse) {
        assignmentList = newAssignmentList;
        course = newCourse;
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
            case 0 -> course.getStudentsEnrolled().get(rowIndex).getFirstName() + " " + course.getStudentsEnrolled().get(rowIndex).get
        }
    }
}
