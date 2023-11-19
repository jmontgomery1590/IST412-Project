package CourseManagement.View;

import CourseManagement.Controller.CourseMgmtController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddLessonUI extends JFrame{
    private JFrame addLessonFrame;
    private JPanel addLessonPanel, readingPanel, titlePanel;
    private JPanel contentPanel;
    private JTextField titleTextField;
    private JTextArea contentTextArea, readingTextArea;
    private JButton saveButton, cancelButton;
    CourseMgmtController courseMgmtCntrl;

    public AddLessonUI(CourseMgmtController courseMgmtController) {
        courseMgmtCntrl = courseMgmtController;
        addLessonFrame = new JFrame("Add New Lesson");
        addLessonFrame.setResizable(false);
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
