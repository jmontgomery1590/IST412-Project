package CourseManagement.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class LessonTableModel extends AbstractTableModel {
    String columnNames[] = {"Lesson Name"};
    private ArrayList<Lesson> lessonList;

    public LessonTableModel(ArrayList<Lesson> newLessonList) {
        lessonList = newLessonList;
    }

    @Override
    public int getRowCount() {
        return lessonList.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return lessonList.get(rowIndex).getPageTitle();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
}
