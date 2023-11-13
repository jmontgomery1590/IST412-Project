package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;

import javax.swing.*;

public class ViewLessonUI {
    private JPanel lessonPanel, lessonViewPanel;
    private JLabel lessonPanelLessonLabel;
    private CourseMgmtController courseMgmtCntrl;

    public JPanel getLessonViewPanel() {
        return lessonViewPanel;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}
