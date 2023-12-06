package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditAssignmentUI extends JFrame {
    private JPanel editAssignmentPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel buttonPanel;
    private JButton saveAssignmentButton;
    private JButton cancelButton;
    private JButton editQuestionButton;
    private JPanel mainPanel;
    private JPanel assignmentNamePanel;
    private JTextField assignmentNameTextField;
    private JButton submitButton;
    private JPanel listPanel;
    private JList questionList;
    private JLabel questionListLabel;
    private JFrame editAssignmentFrame;
    private CourseworkMgmtController courseworkMgmtController;
    private Assignment assignment;

    public EditAssignmentUI(CourseworkMgmtController courseworkMgmtController, Assignment assignment){
        this.courseworkMgmtController = courseworkMgmtController;
        this.assignment = assignment;
        initComponents();
    }

    private void initComponents(){
        editAssignmentFrame = new JFrame("Assignment Builder");
        editAssignmentFrame.setResizable(false);
        editAssignmentFrame.setMinimumSize(new Dimension(700, 600));
        editAssignmentFrame.setContentPane(editAssignmentPanel);
        editAssignmentFrame.setLocationRelativeTo(null);
        this.editQuestionButton.setEnabled(false);
        this.saveAssignmentButton.setEnabled(false);
        editAssignmentFrame.setVisible(true);
        editAssignmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        assignmentNameTextField.setText(assignment.getAssignmentTitle());
        addALButtons();
        addFocusListeners();
    }

    private void onWindowClosing() {
        courseworkMgmtController.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
        courseworkMgmtController.getCourseMgmtController().getHomepageController().getHomepageUI().transferFocus();
    }



    private void addFocusListeners() {
        editAssignmentFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });
        assignmentNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (assignmentNameTextField.getText().equals("Enter Assignment Name Here")) {
                    assignmentNameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (assignmentNameTextField.getText().isEmpty()) {
                    assignmentNameTextField.setText("Enter Assignment Name Here");
                }
            }
        });

        editAssignmentFrame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                super.windowGainedFocus(e);

                Question[] questionArray = assignment.getQuestionList().getQuestionList().toArray(new Question[0]);
                questionList.setListData(questionArray);
            }
        });
    }

    private void addALButtons(){

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confirmAssignmentIsValid(assignmentNameTextField.getText()))
                {
                    assignmentNameTextField.setEnabled(false);
                    submitButton.setEnabled(false);
                    editQuestionButton.setEnabled(true);
                    assignment.setAssignmentTitle(assignmentNameTextField.getText());
                    courseworkMgmtController.setAssignment(assignment);
                }
            }
        });

        editQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Question selectedquestion = assignment.getQuestionList().getQuestionList().get(questionList.getSelectedIndex());
                courseworkMgmtController.setEditQuestionUI(new EditQuestionUI(courseworkMgmtController, selectedquestion));
                editAssignmentFrame.setEnabled(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtController.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                editAssignmentFrame.dispose();
                courseworkMgmtController.setAssignmentInterface(null);
            }
        });
    }

    private boolean isNewAssignmentName(String assignmentName){
        for (Assignment assignment : courseworkMgmtController.getCurrentCourse().getAssignmentList().getAssignments()) {
            if (assignment.getAssignmentTitle().equalsIgnoreCase(assignmentName) && !assignment.getAssignmentTitle().equals(assignmentName))
            {
                return false;
            }
        }
        return true;
    }

    public void checkAssignmentForQuestions()
    {
        if (!assignment.getQuestionList().getQuestionList().isEmpty())
        {
            saveAssignmentButton.setEnabled(true);
        }
    }

    private boolean confirmAssignmentIsValid(String string){
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
            assignmentNameTextField.setText("This assignment name already exists!");
            return false;
        }
        else if (string.equals("This assignment name already exists!"))
        {
            assignmentNameTextField.setText("Enter Assignment Name Here");
            return false;
        }
        else
        {
            return true;
        }
    }


    public JPanel getEditAssignmentPanel() {
        return editAssignmentPanel;
    }

    public void setEditAssignmentPanel(JPanel editAssignmentPanel) {
        this.editAssignmentPanel = editAssignmentPanel;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JButton getSaveAssignmentButton() {
        return saveAssignmentButton;
    }

    public void setSaveAssignmentButton(JButton saveAssignmentButton) {
        this.saveAssignmentButton = saveAssignmentButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JButton getEditQuestionButton() {
        return editQuestionButton;
    }

    public void setEditQuestionButton(JButton editQuestionButton) {
        this.editQuestionButton = editQuestionButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getAssignmentNamePanel() {
        return assignmentNamePanel;
    }

    public void setAssignmentNamePanel(JPanel assignmentNamePanel) {
        this.assignmentNamePanel = assignmentNamePanel;
    }

    public JTextField getAssignmentNameTextField() {
        return assignmentNameTextField;
    }

    public void setAssignmentNameTextField(JTextField assignmentNameTextField) {
        this.assignmentNameTextField = assignmentNameTextField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }

    public JPanel getListPanel() {
        return listPanel;
    }

    public void setListPanel(JPanel listPanel) {
        this.listPanel = listPanel;
    }

    public JList getQuestionList() {
        return questionList;
    }

    public void setQuestionList(JList questionList) {
        this.questionList = questionList;
    }

    public JLabel getQuestionListLabel() {
        return questionListLabel;
    }

    public void setQuestionListLabel(JLabel questionListLabel) {
        this.questionListLabel = questionListLabel;
    }

    public JFrame getEditAssignmentFrame() {
        return editAssignmentFrame;
    }

    public void setEditAssignmentFrame(JFrame editAssignmentFrame) {
        this.editAssignmentFrame = editAssignmentFrame;
    }

    public CourseworkMgmtController getCourseworkMgmtController() {
        return courseworkMgmtController;
    }

    public void setCourseworkMgmtController(CourseworkMgmtController courseworkMgmtController) {
        this.courseworkMgmtController = courseworkMgmtController;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
