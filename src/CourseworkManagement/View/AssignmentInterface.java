package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;
import CourseworkManagement.Model.QuestionList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class AssignmentInterface extends JFrame {
    public AssignmentInterface (CourseworkMgmtController courseworkMgmtCntrl) {
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


    // This method will be called when the window is closing
    private void onWindowClosing() {
        courseworkMgmtCntrl.getCourseworkMgmtInterface().getView().setEnabled(true);
        courseworkMgmtCntrl.getCourseworkMgmtInterface().getView().transferFocus();
    }

    private void addALButtons(){
        this.addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtCntrl.setQuestionInterface(new QuestionInterface(courseworkMgmtCntrl));
                assignmentFrame.setEnabled(false);
            }
        });
        this.cancelButton.addActionListener(this.courseworkMgmtCntrl);
        this.createAssignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAssignment.updatePossibleScore();
                courseworkMgmtCntrl.getAssignmentList().getAssignments().add(currentAssignment);
                courseworkMgmtCntrl.getCourseworkMgmtInterface().getView().setEnabled(true);
                assignmentFrame.dispose();
            }
        });
        this.submitButton.addActionListener(e -> {
            if (!assignmentNameTextField.getText().isEmpty() && !Objects.equals(assignmentNameTextField.getText(), "Enter Assignment Name Here"))
            {
                isValidAssignmentName = true;
                addQuestionButton.setEnabled(true);
                createAssignmentButton.setEnabled(true);
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

    public JPanel getNewAssignmentPanel() {
        return newAssignmentPanel;
    }

    public void setNewAssignmentPanel(JPanel newAssignmentPanel) {
        this.newAssignmentPanel = newAssignmentPanel;
    }

    public JButton getCreateAssignmentButton() {
        return createAssignmentButton;
    }

    public void setCreateAssignmentButton(JButton createAssignmentButton) {
        this.createAssignmentButton = createAssignmentButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
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

    public JTextField getAssignmentNameTextField() {
        return assignmentNameTextField;
    }

    public void setAssignmentNameTextField(JTextField assignmentNameTextField) {
        this.assignmentNameTextField = assignmentNameTextField;
    }

    public JButton getAddQuestionButton() {
        return addQuestionButton;
    }

    public void setAddQuestionButton(JButton addQuestionButton) {
        this.addQuestionButton = addQuestionButton;
    }

    public JList getQuestionList() {
        return questionList;
    }

    public void setQuestionList(JList questionList) {
        this.questionList = questionList;
    }

    public JPanel getListPanel() {
        return listPanel;
    }

    public void setListPanel(JPanel listPanel) {
        this.listPanel = listPanel;
    }

    public JPanel getAssignmentNamePanel() {
        return assignmentNamePanel;
    }

    public void setAssignmentNamePanel(JPanel assignmentNamePanel) {
        this.assignmentNamePanel = assignmentNamePanel;
    }

    public JFrame getAssignmentFrame() {
        return assignmentFrame;
    }

    public void setAssignmentFrame(JFrame assignmentFrame) {
        this.assignmentFrame = assignmentFrame;
    }
}
