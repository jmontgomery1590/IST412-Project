package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class AddAssignmentUI extends JFrame {
    public AddAssignmentUI(CourseworkMgmtController courseworkMgmtCntrl) {
        this.courseworkMgmtCntrl = courseworkMgmtCntrl;
        this.currentAssignment = new Assignment("");
        initComponents();
    }

    private void initComponents(){
        assignmentFrame = new JFrame("Assignment Builder");
        assignmentFrame.setResizable(false);
        assignmentFrame.setMinimumSize(new Dimension(700, 600));
        assignmentFrame.setContentPane(newAssignmentPanel);
        assignmentFrame.setLocationRelativeTo(null);
        this.addQuestionButton.setEnabled(false);
        this.createAssignmentButton.setEnabled(false);
        this.isValidAssignmentName = false;
        assignmentFrame.setVisible(true);
        assignmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addALButtons();
        addFocusListeners();
    }

    private void onWindowClosing() {
        courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
        courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().transferFocus();
    }

    private void addALButtons(){
        this.addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtCntrl.setQuestionInterface(new AddQuestionUI(courseworkMgmtCntrl));
                assignmentFrame.setEnabled(false);
            }
        });
        this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                assignmentFrame.dispose();
                courseworkMgmtCntrl.setAssignmentInterface(null);
            }
        });
        this.createAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAssignment.updatePossibleScore();
                courseworkMgmtCntrl.getAssignmentList().getAssignments().add(currentAssignment);
                courseworkMgmtCntrl.getDatabase().addAssignmentToDatabase(currentAssignment, courseworkMgmtCntrl.getCurrentCourse());
                courseworkMgmtCntrl.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                courseworkMgmtCntrl.getCourseAssignmentTableModel().fireTableDataChanged();
                if (courseworkMgmtCntrl.getAssignmentByStudentTablemodel() != null)
                {
                    courseworkMgmtCntrl.loadAllStudentsAssignmentList();
                    courseworkMgmtCntrl.getCourseworkMgmtInterface().getAssignmentTable().setModel(courseworkMgmtCntrl.getAssignmentByStudentTablemodel());
                }
                assignmentFrame.dispose();
            }
        });
        this.submitButton.addActionListener(e -> {
            if (confirmIsNewAssignment(assignmentNameTextField.getText()))
            {
                isValidAssignmentName = true;
                addQuestionButton.setEnabled(true);
                assignmentNameTextField.setEnabled(false);
                submitButton.setEnabled(false);
                currentAssignment.setAssignmentTitle(assignmentNameTextField.getText());
                courseworkMgmtCntrl.setAssignment(currentAssignment);
            }
            else
            {
                isValidAssignmentName = false;
                addQuestionButton.setEnabled(false);
                createAssignmentButton.setEnabled(false);
            }
        });
    }

    private boolean isNewAssignmentName(String assignmentName){
        for (Assignment assignment : courseworkMgmtCntrl.getCurrentCourse().getAssignmentList().getAssignments()) {
            if (assignment.getAssignmentTitle().equalsIgnoreCase(assignmentName))
            {
                assignmentNameTextField.setText("This assignment name already exists!");
                return false;
            }
        }
        return true;
    }

    private void addFocusListeners(){
        assignmentFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });
        assignmentNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (assignmentNameTextField.getText().equals("Enter Assignment Name Here")){
                    assignmentNameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (assignmentNameTextField.getText().isEmpty()){
                    assignmentNameTextField.setText("Enter Assignment Name Here");
                }
            }
        });

        assignmentFrame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                super.windowGainedFocus(e);

                Question[] questionArray = currentAssignment.getQuestionList().getQuestionList().toArray(new Question[0]);
                questionList.setListData(questionArray);
            }
        });
    }

    public void checkAssignmentForQuestions()
    {
        if (!currentAssignment.getQuestionList().getQuestionList().isEmpty())
        {
            createAssignmentButton.setEnabled(true);
        }
    }

    private boolean confirmIsNewAssignment(String string){
        if (string.isEmpty())
        {
            return false;
        }
        else if (string.equals("Enter Assignment Name Here"))
        {
            return false;
        }
        else if (!isNewAssignmentName(string))
        {
            return false;
        }
        else if (string.equals("This assignment name already exists!"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    private Assignment currentAssignment;
    private boolean isValidAssignmentName;
    private JFrame assignmentFrame;
    private CourseworkMgmtController courseworkMgmtCntrl;
    private JPanel newAssignmentPanel;
    private JButton createAssignmentButton;
    private JButton cancelButton;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel buttonPanel;
    private JTextField assignmentNameTextField;
    private JButton addQuestionButton;
    private JList questionList;
    private JPanel listPanel;
    private JPanel assignmentNamePanel;
    private JLabel questionListLabel;
    private JButton submitButton;
    private JScrollPane questionsScrollPane;

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JFrame getAssignmentFrame() {
        return assignmentFrame;
    }

    public Assignment getCurrentAssignment() {
        return currentAssignment;
    }
}