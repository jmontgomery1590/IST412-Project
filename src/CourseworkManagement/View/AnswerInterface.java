package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class AnswerInterface  extends JFrame {
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
    private CourseworkMgmtController courseworkMgmtCntrl;

    public AnswerInterface(CourseworkMgmtController courseworkMgmtController){
        courseworkMgmtCntrl = courseworkMgmtController;
        answerFrame = new JFrame("Answer Builder");
        questionDescriptionLabel.setText(courseworkMgmtCntrl.getQuestionInterface().getQuestionTextField().getText());
        answerFrame.setResizable(false);
        answerFrame.setMinimumSize(new Dimension(700, 600));
        answerFrame.setContentPane(answerInterfacePanel);
        answerFrame.setLocationRelativeTo(null);
        answerFrame.setVisible(true);
        answerFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        answerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });
    }

    private void onWindowClosing() {
        courseworkMgmtCntrl.getAssignmentInterface().getAssignmentFrame().setEnabled(true);
        courseworkMgmtCntrl.getAssignmentInterface().getAssignmentFrame().transferFocus();
    }

    public JFrame getAnswerFrame() {
        return answerFrame;
    }

    public void setAnswerFrame(JFrame answerFrame) {
        this.answerFrame = answerFrame;
    }

    public JButton getSaveAnswerButton() {
        return saveAnswerButton;
    }

    public void setSaveAnswerButton(JButton saveAnswerButton) {
        this.saveAnswerButton = saveAnswerButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
}
