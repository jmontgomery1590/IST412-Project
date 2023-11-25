package CourseManagement.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AnnouncementTableModel extends AbstractTableModel {
    String columnNames[] = {"Announcement Title"};
    private ArrayList<Announcement> announcementList;

    public AnnouncementTableModel(ArrayList<Announcement> newAnnouncementList) {
        announcementList = newAnnouncementList;
    }

    @Override
    public int getRowCount() {
        return announcementList.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return announcementList.get(rowIndex).getPageTitle();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
}
