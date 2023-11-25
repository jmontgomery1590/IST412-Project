package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.LessonTableModel;
import UserAuthentication.Controller.HomepageController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LessonMgmtUI {
    private HomepageController homepageController;
    private LessonMgmtUI lessonMgmtUI;
    private JFrame lessonMgmtFrame;
    private JPanel crudPanel;
    private JPanel lessonMgmtPanel, lessonTablePanel;
    private JButton addLessonButton, editLessonButton, viewLessonButton, deleteLessonButton;
    private JTable lessonTable;
    private JLabel lessonLabel;
    private JScrollPane tableScrollPane;
    private CourseMgmtController courseMgmtCntrl;
    private int lessonListPosition;

    public LessonMgmtUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        courseMgmtCntrl.loadLessonList();
        courseMgmtCntrl.setLessonTable(new LessonTableModel(courseMgmtCntrl.getLessonList().getLessons()));
        lessonLabel.setText(courseMgmtCntrl.getSelectedCourse().getCourseID() + " " + courseMgmtCntrl.getSelectedCourse().getCourseName());
        lessonTable.setModel(courseMgmtCntrl.getLessonTable());
        lessonMgmtFrame = new JFrame("Lessons");
        addALLessonButtons();
        //verifyButtonAccess();
    }

    /*private void verifyButtonAccess() {
        if (homepageController.getUser().getLoginID().equalsIgnoreCase("Student") || homepageController.getUser().getLoginID().equalsIgnoreCase("TA"))
        {
            lessonMgmtUI.getAddLessonButton().setVisible(false);
            lessonMgmtUI.getDeleteLessonButton().setVisible(false);
            lessonMgmtUI.getEditLessonButton().setVisible(false);
        }
        else if (homepageController.getUser().getLoginID().equalsIgnoreCase("Instructor"))
        {
            lessonMgmtUI.getAddLessonButton().setVisible(false);
            lessonMgmtUI.getDeleteLessonButton().setVisible(false);
        }
    }*/

    public void addALLessonButtons() {
        this.getViewLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessonListPosition = lessonTable.getSelectedRow();
                //courseMgmtCntrl.getCourseMgmtUI().setSelectedLesson(courseMgmtCntrl.getLessonList().getLessons().get(lessonTable.getSelectedRow() + 1));
                courseMgmtCntrl.setViewLessonUI(new ViewLessonUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().add(courseMgmtCntrl.getViewLessonUI().getLessonPanel(), "View Lesson");

                courseMgmtCntrl.getHomepageController().getHomepageUI().getCardSwapper().show(courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel(), "View Lesson");
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseMgmtCntrl.getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
        this.getAddLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.setAddLessonUI(new AddLessonUI(courseMgmtCntrl));
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
            }
        });
        this.getEditLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.getDeleteLessonButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = lessonTable.getSelectedRow();
                courseMgmtCntrl.getLessonList().getLessons().remove(selectedRow);
                courseMgmtCntrl.getLessonTable().fireTableDataChanged();
            }
        });
    }

    public JButton getAddLessonButton() {
        return addLessonButton;
    }

    public JButton getEditLessonButton() {
        return editLessonButton;
    }

    public JButton getViewLessonButton() {
        return viewLessonButton;
    }

    public JButton getDeleteLessonButton() {
        return deleteLessonButton;
    }

    public JPanel getLessonMgmtPanel() {
        return lessonMgmtPanel;
    }

    public int getLessonListPosition() {
        return lessonListPosition;
    }

    public void setLessonListPosition(int lessonListPosition) {
        this.lessonListPosition = lessonListPosition;
    }
}
