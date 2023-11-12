package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;

import javax.swing.*;

public class ViewLessonUI {
    private JPanel lessonPanel;
    private JPanel lessonViewPanel;
    private JLabel lessonPanelLessonLabel;
    private CourseMgmtController courseMgmtCntrl;

    public JPanel getLessonPanel() {
        return lessonPanel;
    }

    public void setLessonPanel(JPanel lessonPanel) {
        this.lessonPanel = lessonPanel;
    }

    public JPanel getLessonViewPanel() {
        return lessonViewPanel;
    }

    public void setLessonViewPanel(JPanel lessonViewPanel) {
        this.lessonViewPanel = lessonViewPanel;
    }

    public JLabel getLessonPanelLessonLabel() {
        return lessonPanelLessonLabel;
    }

    public void setLessonPanelLessonLabel(JLabel lessonPanelLessonLabel) {
        this.lessonPanelLessonLabel = lessonPanelLessonLabel;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }
}
