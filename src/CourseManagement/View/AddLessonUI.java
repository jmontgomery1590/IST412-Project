package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Lesson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddLessonUI extends JFrame{
    private JFrame addLessonFrame;
    private JPanel addLessonPanel, readingPanel, titlePanel, contentPanel;
    private JTextField titleTextField;
    private JTextArea lessonContentArea, lessonReadingArea;
    private JButton saveButton, cancelButton;
    private JScrollPane lessonContentScrollPane, readingScrollPane;
    CourseMgmtController courseMgmtCntrl;

    public AddLessonUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;

        initComponents();
    }
    
    private void initComponents() {
        addLessonFrame = new JFrame("Add New Lesson");
        addLessonFrame.setMinimumSize(new Dimension(800, 600));
        addLessonFrame.setContentPane(addLessonPanel);
        addLessonFrame.setLocationRelativeTo(null);
        addLessonFrame.setVisible(true);
        addLessonFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addALAddLessonButtons();
        addFocusListeners();
    }

    public void addALAddLessonButtons()
    {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int courseTableID = courseMgmtCntrl.getSelectedCourse().getCourseTableID();
                String pageTitle = titleTextField.getText();
                String lessonContent = lessonContentArea.getText();
                String readingArea = lessonReadingArea.getText();
                courseMgmtCntrl.getLessonMgmtUI().setNewLesson(new Lesson(courseTableID, pageTitle, lessonContent, readingArea));

                courseMgmtCntrl.getLessonList().getLessons().add(courseMgmtCntrl.getLessonMgmtUI().getNewLesson());
                courseMgmtCntrl.getDatabase().addLessonToDatabase(courseMgmtCntrl.getLessonMgmtUI());

                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                courseMgmtCntrl.getLessonTable().fireTableDataChanged();
                addLessonFrame.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                addLessonFrame.dispose();
                courseMgmtCntrl.setAddLessonUI(null);
            }
        });
    }

    private void onWindowClosing() {
        courseMgmtCntrl.getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
        courseMgmtCntrl.getHomepageController().getHomepageUI().transferFocus();
    }

    private void addFocusListeners()
    {
        addLessonFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
