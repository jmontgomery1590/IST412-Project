package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.AnnouncementList;
import CourseManagement.Model.AnnouncementTableModel;
import CourseManagement.Model.LessonList;
import CourseManagement.Model.LessonTableModel;
import CourseworkManagement.Controller.CourseworkMgmtController;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageMgmtUI {
    private JFrame viewPageMgmtFrame;
    private JPanel pageMgmtPanel;
    private JButton lessonsButton;
    private JButton announcementsButton;
    private JButton assignmentsButton;
    private JPanel buttonPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private LessonMgmtUI lessonMgmtUI;
    private AnnouncementMgmtUI announcementMgmtUI;
    private HomepageController homepageController;
    private CourseMgmtController courseMgmtCntrl;
    private LessonTableModel lessonTable;
    private AnnouncementTableModel announcementTable;
    private LessonList lessonList;
    private AnnouncementList announcementList;

    public PageMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;

        this.lessonMgmtUI = new LessonMgmtUI(this.courseMgmtCntrl);
        this.announcementMgmtUI = new AnnouncementMgmtUI(this.courseMgmtCntrl);
        viewPageMgmtFrame = new JFrame("Page Selection");
        this.addALPageButtons();
    }

    public void addALPageButtons() {
        this.getLessonsButton().addActionListener(this.courseMgmtCntrl);
        this.getAnnouncementsButton().addActionListener(this.courseMgmtCntrl);
        this.getAssignmentsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setCourseworkMgmtCntrl(new CourseworkMgmtController(courseMgmtCntrl));
            }
        });
    }

    /*@Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == this.getLessonsButton())
        {
            if (this.getLessonMgmtUI() != null)
            {
                this.setLessonMgmtUI(new LessonMgmtUI(this.courseMgmtCntrl));
                this.lessonMgmtUI.addALLessonButtons();
                this.homepageController.getHomepageUI().getViewPanel().add(this.lessonMgmtUI.getLessonMgmtPanel(), "View Lesson");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Course");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }

        if (obj == this.getAnnouncementsButton())
        {
            if (this.getAnnouncementMgmtUI() != null)
            {
                this.setLessonMgmtUI(new LessonMgmtUI(this.courseMgmtCntrl));
                this.announcementMgmtUI.addALAnnouncementButtons();
                this.homepageController.getHomepageUI().getViewPanel().add(this.announcementMgmtUI.getAnnouncementMgmtPanel(), "View Lesson");
            }
            this.homepageController.getHomepageUI().getCardSwapper().show(this.homepageController.getHomepageUI().getViewPanel(), "View Course");
            this.homepageController.getHomepageUI().getViewPanel().revalidate();
            this.homepageController.getHomepageUI().getViewPanel().repaint();
        }
    }*/

    public JFrame getViewPageMgmtUI() {
        return viewPageMgmtFrame;
    }

    public JButton getLessonsButton() {
        return lessonsButton;
    }


    public JButton getAnnouncementsButton() {
        return announcementsButton;
    }

    public JButton getAssignmentsButton() {
        return assignmentsButton;
    }

    public JPanel getPageMgmtPanel() {
        return pageMgmtPanel;
    }

    public CourseMgmtController getCourseMgmtCntrl() {
        return courseMgmtCntrl;
    }

    public void setCourseMgmtCntrl(CourseMgmtController courseMgmtCntrl) {
        this.courseMgmtCntrl = courseMgmtCntrl;
    }

    public LessonMgmtUI getLessonMgmtUI() {
        return lessonMgmtUI;
    }

    public void setLessonMgmtUI(LessonMgmtUI lessonMgmtUI) {
        this.lessonMgmtUI = lessonMgmtUI;
    }

    public AnnouncementMgmtUI getAnnouncementMgmtUI() {
        return announcementMgmtUI;
    }

    public void setAnnouncementMgmtUI(AnnouncementMgmtUI announcementMgmtUI) {
        this.announcementMgmtUI = announcementMgmtUI;
    }

    public HomepageController getHomepageController() {
        return homepageController;
    }

    public void setHomepageController(HomepageController homepageController) {
        this.homepageController = homepageController;
    }

    public LessonTableModel getLessonTable() {
        return lessonTable;
    }

    public void setLessonTable(LessonTableModel lessonTable) {
        this.lessonTable = lessonTable;
    }

    public AnnouncementTableModel getAnnouncementTable() {
        return announcementTable;
    }

    public void setAnnouncementTable(AnnouncementTableModel announcementTable) {
        this.announcementTable = announcementTable;
    }

    public LessonList getLessonList() {
        return lessonList;
    }

    public void setLessonList(LessonList lessonList) {
        this.lessonList = lessonList;
    }

    public AnnouncementList getAnnouncementList() {
        return announcementList;
    }

    public void setAnnouncementList(AnnouncementList announcementList) {
        this.announcementList = announcementList;
    }
}
