package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class QuestionInterface extends JFrame {

    public QuestionInterface(CourseworkMgmtController courseworkMgmtController){
        courseworkMgmtCntrl = courseworkMgmtController;
        questionFrame = new JFrame("Question Builder");
        questionComboBox.addItem("Multiple Choice");
        questionComboBox.addItem("Open Ended");
        questionFrame.setResizable(false);
        questionFrame.setMinimumSize(new Dimension(700, 600));
        questionFrame.setContentPane(questionViewPanel);
        questionFrame.setLocationRelativeTo(null);
        questionFrame.setVisible(true);
        questionFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        questionFrame.addWindowListener(new WindowAdapter() {
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

    private CourseworkMgmtController courseworkMgmtCntrl;
    private JFrame questionFrame;
    private JPanel questionViewPanel;
    private JPanel titlePanel;
    private JButton saveQuestionButton;
    private JButton cancelButton;
    private JComboBox questionComboBox;
    private JTextField questionTextField;
    private JLabel questionTypeLabel;
    private JLabel questionLabel;
    private JLabel questionTitleLabel;
    private JList answerList;
    private JLabel answerListLabel;
    private JPanel answerListPanel;
    private JPanel bodyPanel;
    private JButton addAnswerButton;

    public JButton getSaveQuestionButton() {
        return saveQuestionButton;
    }

    public void setSaveQuestionButton(JButton saveQuestionButton) {
        this.saveQuestionButton = saveQuestionButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JList getAnswerList() {
        return answerList;
    }

    public void setAnswerList(JList answerList) {
        this.answerList = answerList;
    }

    public JButton getAddAnswerButton() {
        return addAnswerButton;
    }

    public void setAddAnswerButton(JButton addAnswerButton) {
        this.addAnswerButton = addAnswerButton;
    }

    public JFrame getQuestionFrame() {
        return questionFrame;
    }

    public void setQuestionFrame(JFrame questionFrame) {
        this.questionFrame = questionFrame;
    }

    public JTextField getQuestionTextField() {
        return questionTextField;
    }

    public void setQuestionTextField(JTextField questionTextField) {
        this.questionTextField = questionTextField;
    }
}
