package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;

import javax.swing.*;

public class ViewPageUI {
    private JPanel pagePanel;
    private JPanel pageViewLabel;
    private JLabel pagePanelPageLabel;
    private CourseMgmtController courseMgmtCntrl;

    public JPanel getPagePanel() {
        return pagePanel;
    }

    public void setPagePanel(JPanel pagePanel) {
        this.pagePanel = pagePanel;
    }

    public JPanel getPageViewLabel() {
        return pageViewLabel;
    }

    public void setPageViewLabel(JPanel pageViewLabel) {
        this.pageViewLabel = pageViewLabel;
    }

    public JLabel getPagePanelPageLabel() {
        return pagePanelPageLabel;
    }

    public void setPagePanelPageLabel(JLabel pagePanelPageLabel) {
        this.pagePanelPageLabel = pagePanelPageLabel;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}
