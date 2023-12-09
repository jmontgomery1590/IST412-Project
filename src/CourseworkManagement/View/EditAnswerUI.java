package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.MultipleChoiceAnswer;
import CourseworkManagement.Model.OpenEndedAnswer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditAnswerUI extends JFrame {
    private JPanel editAnswerPanel;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JButton saveAnswerButton;
    private JButton cancelButton;
    private JPanel bodyPanel;
    private JLabel answerLabel;
    private JTextField answerField;
    private JLabel questionLabel;
    private JLabel questionDescriptionLabel;
    private JButton submitButton;
    private JPanel radioPanel;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JLabel radioLabel;
    private JTextArea questionTextArea;
    private CourseworkMgmtController courseworkMgmtController;
    private Answer answerToEdit;
    private JFrame editAnswerFrame;

    public EditAnswerUI(CourseworkMgmtController courseworkMgmtController, Answer answer) {
        this.courseworkMgmtController = courseworkMgmtController;
        this.answerToEdit = answer;
        editAnswerFrame = new JFrame("Answer Builder");
        questionTextArea.setText(courseworkMgmtController.getEditQuestionUI().getQuestionTextField().getText());
        editAnswerFrame.setResizable(false);
        editAnswerFrame.setMinimumSize(new Dimension(700, 600));
        editAnswerFrame.setContentPane(editAnswerPanel);
        editAnswerFrame.setLocationRelativeTo(null);
        this.saveAnswerButton.setEnabled(false);
        this.checkAnswerType();
        editAnswerFrame.setVisible(true);
        editAnswerFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addALButtons();
        this.AddFocusListeners();
    }

    private void checkAnswerType(){
        if (answerToEdit.getClass().equals(OpenEndedAnswer.class))
        {
            yesRadioButton.setSelected(true);
            noRadioButton.setEnabled(false);
            yesRadioButton.setEnabled(false);
        }
        else if (answerToEdit.getClass().equals(MultipleChoiceAnswer.class))
        {
            if (answerToEdit.getIsCorrect())
            {
                yesRadioButton.setSelected(true);
            }
            else
            {
                noRadioButton.setSelected(true);
            }
        }
    }

    private void AddFocusListeners(){
        editAnswerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });
        answerField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (answerField.getText().equals("Insert a potential answer here:")){
                    answerField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (answerField.getText().isEmpty()){
                    answerField.setText("Insert a potential answer here:");
                }
            }
        });
    }

    private void onWindowClosing() {
        courseworkMgmtController.getEditQuestionUI().getEditQuestionFrame().setEnabled(true);
        courseworkMgmtController.getEditQuestionUI().getEditQuestionFrame().transferFocus();
    }

    private void addALButtons() {
        this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtController.getEditQuestionUI().getEditQuestionFrame().setEnabled(true);
                editAnswerFrame.dispose();
                courseworkMgmtController.setEditAnswerUI(null);
                courseworkMgmtController.getEditAssignmentUI().getEditAssignmentFrame().transferFocus();
            }
        });

        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verifyAnswer())
                {
                    saveAnswerButton.setEnabled(true);
                    answerField.setEnabled(false);
                    yesRadioButton.setEnabled(false);
                    noRadioButton.setEnabled(false);
                    submitButton.setEnabled(false);
                }
            }
        });

        this.saveAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerToEdit.setAnswer(answerField.getText());
                markAsCorrect(answerToEdit);
                courseworkMgmtController.getEditQuestionUI().getEditQuestionFrame().setEnabled(true);
                courseworkMgmtController.getEditQuestionUI().getSaveQuestionButton().setEnabled(true);
                courseworkMgmtController.getEditQuestionUI().getSaveQuestionButton().setEnabled(courseworkMgmtController.getEditQuestionUI().checkIfAnyAnswersAreCorrect());
                courseworkMgmtController.getEditQuestionUI().setUpAnswerListData();
                editAnswerFrame.dispose();
            }
        });
    }

    private boolean verifyAnswer() {
        String answer = this.answerField.getText();
        if ((!yesRadioButton.isSelected() && !noRadioButton.isSelected()))
        {
            return false;
        }
        else if (answer.isEmpty())
        {
            return false;
        }
        else if (answer.equals("Insert a potential answer here:"))
        {
            return false;
        }
        else
        {
            this.saveAnswerButton.setEnabled(false);
            return true;
        }
    }

    private void markAsCorrect(Answer answer){
        if (yesRadioButton.isSelected())
        {
            answer.markCorrect();
        }
        else
        {
            answer.markIncorrect();
        }
    }
}
