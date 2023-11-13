package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.*;
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
        this.titleLabel.setText(this.courseMgmtCntrl.getSelectedCourse().getCourseID() + " " + this.courseMgmtCntrl.getSelectedCourse().getCourseName());
        this.addALPageButtons();
    }

    public void addALPageButtons() {
        this.getLessonsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setLessonMgmtUI(new LessonMgmtUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().add(courseMgmtCntrl.getLessonMgmtUI().getLessonMgmtPanel(), "Lesson Management");

                courseMgmtCntrl.getHomepageController().getHomepageUI().getCardSwapper().show(courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel(), "Lesson Management");
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
        this.getAnnouncementsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setAnnouncementMgmtUI(new AnnouncementMgmtUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().add(courseMgmtCntrl.getAnnouncementMgmtUI().getAnnouncementMgmtPanel(), "Announcement Panel");

                courseMgmtCntrl.getHomepageController().getHomepageUI().getCardSwapper().show(courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel(), "Announcement Panel");
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
        this.getAssignmentsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setCourseworkMgmtCntrl(new CourseworkMgmtController(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().add(courseMgmtCntrl.getCourseworkMgmtCntrl().getCourseworkMgmtInterface().getCourseworkPanel(), "Course Work");

                courseMgmtCntrl.getHomepageController().getHomepageUI().getCardSwapper().show(courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel(), "Course Work");
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
    }

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
