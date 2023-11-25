package CourseManagement.Model;

import CourseManagement.Model.Course;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CourseTableModel extends AbstractTableModel {
    String columnNames[] = {"Course ID", "Course Name", "Instructor", "Max Enrolled"};
    private ArrayList<Course> courseList;

    public CourseTableModel(ArrayList<Course> newCourseList){
        courseList = newCourseList;
    }

    @Override
    public int getRowCount(){
        return courseList.size();
    }

    @Override
    public int getColumnCount() { return 4; }

    /**
     * Checks for which row is selected
     * @param rowIndex        the row whose value is to be queried
     * @param columnIndex     the column whose value is to be queried
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> courseList.get(rowIndex).getCourseID();
            case 1 -> courseList.get(rowIndex).getCourseName();
            case 2 -> courseList.get(rowIndex).getInstructor().getFirstName() + " " + courseList.get(rowIndex).getInstructor().getLastName();
            case 3 -> courseList.get(rowIndex).getMaxEnrolled();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int col) {return columnNames[col];}
}
