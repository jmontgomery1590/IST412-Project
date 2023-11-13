package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnswerUI extends JFrame {

    public AnswerUI(CourseworkMgmtController courseworkMgmtController){
        courseworkMgmtCntrl = courseworkMgmtController;
        answerFrame = new JFrame("Answer Builder");
        questionDescriptionLabel.setText(courseworkMgmtCntrl.getQuestionInterface().getQuestionTextField().getText());
        answerFrame.setResizable(false);
        answerFrame.setMinimumSize(new Dimension(700, 600));
        answerFrame.setContentPane(answerInterfacePanel);
        answerFrame.setLocationRelativeTo(null);
        this.saveAnswerButton.setEnabled(false);
        this.checkQuestionType();
        answerFrame.setVisible(true);
        answerFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addALButtons();
        this.AddFocusListeners();
    }

    private void AddFocusListeners(){
        answerFrame.addWindowListener(new WindowAdapter() {
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

    private void checkQuestionType(){
        if (courseworkMgmtCntrl.getQuestion().getClass().equals(OpenEndedQuestion.class))
        {
            yesRadioButton.setSelected(true);
            noRadioButton.setEnabled(false);
            yesRadioButton.setEnabled(false);
        }
    }

    private void onWindowClosing() {
        courseworkMgmtCntrl.getQuestionInterface().getQuestionFrame().setEnabled(true);
        courseworkMgmtCntrl.getQuestionInterface().getQuestionFrame().transferFocus();
    }

    private void addALButtons() {
        this.cancelButton.addActionListener(this.courseworkMgmtCntrl);
        this.saveAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answerDescr = answerField.getText();
                answer = courseworkMgmtCntrl.getQuestion().createAnswer(answerDescr);
                courseworkMgmtCntrl.getQuestion().getAnswerList().addToList(answer);
                courseworkMgmtCntrl.getQuestionInterface().getQuestionFrame().setEnabled(true);
                answerFrame.dispose();
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
    }

    private boolean verifyAnswer(){
        String answer = this.answerField.getText();
        if ((yesRadioButton.isSelected() || noRadioButton.isSelected()) && (!answer.isEmpty() && !answer.equals("Insert a potential answer here:")))
        {
            return true;
        }
        else
        {
            this.saveAnswerButton.setEnabled(false);
            return false;
        }
    }

    private Answer answer;
    private JFrame answerFrame;
    private JPanel answerInterfacePanel;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel bodyPanel;
    private JTextField answerField;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JLabel radioLabel;
    private JLabel answerLabel;
    private JButton saveAnswerButton;
    private JButton cancelButton;
    private JLabel questionLabel;
    private JLabel questionDescriptionLabel;
    private JPanel radioPanel;
    private JButton submitButton;
    private CourseworkMgmtController courseworkMgmtCntrl;

    public JFrame getAnswerFrame() {
        return answerFrame;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
}
