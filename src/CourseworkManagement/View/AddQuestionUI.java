package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddQuestionUI extends JFrame {

    public AddQuestionUI(CourseworkMgmtController courseworkMgmtController){
        courseworkMgmtCntrl = courseworkMgmtController;
        questionFrame = new JFrame("Question Builder");
        questionComboBox.addItem("Multiple Choice");
        questionComboBox.addItem("Open Ended");
        questionFrame.setResizable(false);
        questionFrame.setMinimumSize(new Dimension(700, 600));
        questionFrame.setContentPane(questionViewPanel);
        questionFrame.setLocationRelativeTo(null);
        this.addAnswerButton.setEnabled(false);
        this.saveQuestionButton.setEnabled(false);
        questionFrame.setVisible(true);
        questionFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.init();
    }

    private void init(){
        addALButtons();
        addFocusListenerTextField();
        setUpAnswerListData();
        isValidPointValue = false;
        isValidQuestion = false;
    }


    public void setUpAnswerListData(){
        DefaultListModel<Answer> model = new DefaultListModel<>();
        answerList.setModel(model);

        if (courseworkMgmtCntrl.getQuestion() != null){
            for (Answer answer : courseworkMgmtCntrl.getQuestion().getAnswerList().getAnswerList())
            {
                model.addElement(answer);
            }
        }
    }

    private void addFocusListenerTextField(){
        questionFrame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                super.windowGainedFocus(e);
                setUpAnswerListData();
            }
        });

        questionFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClosing();
            }
        });
        questionTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (questionTextField.getText().equals("Type your question here:")){
                    questionTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (questionTextField.getText().isEmpty())
                {
                    questionTextField.setText("Type your question here:");
                    isValidQuestion = false;
                    addAnswerButton.setEnabled(false);
                }
                else if (questionTextField.getText().equals("Type your question here:") || questionTextField.getText().equals("This question already exists!"))
                {
                    questionTextField.setText("Type your question here:");
                    isValidQuestion = false;
                    addAnswerButton.setEnabled(false);
                }
                else
                {
                    isValidQuestion = true;
                }
            }
        });

        pointValueTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (pointValueTextField.getText().equals("Type question point value here:") || pointValueTextField.getText().equals("Please enter valid points (Ex: 5 or 5.5)"))
                {
                    pointValueTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (pointValueTextField.getText().isEmpty())
                {
                    pointValueTextField.setText("Type question point value here:");
                    isValidPointValue = false;
                    addAnswerButton.setEnabled(false);
                }
                else
                {
                    if (checkValidDoubleEntered(pointValueTextField.getText()))
                    {
                        isValidPointValue = true;
                    }
                    else
                    {
                        isValidPointValue = false;
                        addAnswerButton.setEnabled(false);
                    }
                }
            }
        });
    }

    public boolean checkValidDoubleEntered(String string){
        try
        {
            double pointValue = Double.parseDouble(string);
            return true;
        }
        catch (NumberFormatException formatException)
        {
            this.pointValueTextField.setText("Please enter valid points (Ex: 5 or 5.5)");
            this.pointValueTextField.setForeground(Color.red);
            return false;
        }
    }

    public void disableButtonAfterAddingOpenEndedAnswer()
    {
        if (currentQuestion.getClass().equals(OpenEndedQuestion.class))
        {
            addAnswerButton.setEnabled(false);
        }
    }

    public boolean checkIfAnyAnswersAreCorrect()
    {
        for (Answer answer : currentQuestion.getAnswerList().getAnswerList())
        {
            if (answer.getIsCorrect())
            {
                return true;
            }
        }
        return false;
    }

    private void addALButtons(){
        this.addAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtCntrl.setAnswerInterface(new AddAnswerUI(courseworkMgmtCntrl));
                questionFrame.setEnabled(false);
            }
        });
        this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtCntrl.getAssignmentInterface().getAssignmentFrame().setEnabled(true);
                questionFrame.dispose();
                courseworkMgmtCntrl.setQuestionInterface(null);
            }
        });
        this.saveQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Question question = courseworkMgmtCntrl.getQuestion();
                courseworkMgmtCntrl.getAssignment().getQuestionList().addToList(question);
                courseworkMgmtCntrl.getAssignmentInterface().getAssignmentFrame().setEnabled(true);
                courseworkMgmtCntrl.getAssignmentInterface().checkAssignmentForQuestions();
                courseworkMgmtCntrl.setQuestion(null);
                questionFrame.dispose();
            }
        });
        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkValidDoubleEntered(pointValueTextField.getText());
                if (isQuestionValid(courseworkMgmtCntrl.getAssignmentInterface().getCurrentAssignment()))
                {
                    addAnswerButton.setEnabled(true);
                    questionComboBox.setEnabled(false);
                    questionTextField.setEnabled(false);
                    pointValueTextField.setEnabled(false);
                    submitButton.setEnabled(false);

                    String question = questionTextField.getText();
                    double pointValue = Double.parseDouble(pointValueTextField.getText());

                    if (questionComboBox.getSelectedItem().equals("Multiple Choice"))
                    {
                        currentQuestion = new MultipleChoiceQuestion(question, pointValue);
                    }
                    else if (questionComboBox.getSelectedItem().equals("Open Ended"))
                    {
                        currentQuestion = new OpenEndedQuestion(question, pointValue);
                    }
                    courseworkMgmtCntrl.setQuestion(currentQuestion);
                }
                else
                {
                    questionTextField.setText("This question already exists!");
                    addAnswerButton.setEnabled(false);
                    saveQuestionButton.setEnabled(false);
                }
            }
        });
    }

    private void onWindowClosing()
    {
        courseworkMgmtCntrl.getAssignmentInterface().getAssignmentFrame().setEnabled(true);
        courseworkMgmtCntrl.getAssignmentInterface().getAssignmentFrame().transferFocus();
    }

    private boolean checkIfQuestionIsNew(Assignment assignment){
        for (Question question : assignment.getQuestionList().getQuestionList())
        {
            if (question.getQuestion().equalsIgnoreCase(questionTextField.getText()))
            {
                return false;
            }
        }
        return true;
    }

    private boolean isQuestionValid(Assignment assignment){
        if (!isValidQuestion)
        {
            return false;
        }
        else if (!isValidPointValue)
        {
            return false;
        }
        else if (!checkIfQuestionIsNew(assignment))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private Question currentQuestion;
    private boolean isValidQuestion;
    private boolean isValidPointValue;
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
    private JLabel pointValueLabel;
    private JTextField pointValueTextField;
    private JButton submitButton;
    private JScrollPane answerScrollPane;

    public JButton getSaveQuestionButton() {
        return saveQuestionButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JFrame getQuestionFrame() {
        return questionFrame;
    }

    public JTextField getQuestionTextField() {
        return questionTextField;
    }
}