package CourseManagement.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PageTableModel extends AbstractTableModel {
    String columnNames[] = {"Page Name"};
    private ArrayList<Page> pageList;

    public PageTableModel(ArrayList<Page> newPageList) {
        pageList = newPageList;
    }

    @Override
    public int getRowCount() {
        return pageList.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return pageList.get(rowIndex).getPageTitle();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
}
