package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.OpenEndedQuestion;
import CourseworkManagement.Model.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditQuestionUI extends JFrame {
    private JPanel questionViewPanel;
    private JPanel bodyPanel;
    private JPanel answerListPanel;
    private JList answerList;
    private JLabel answerListLabel;
    private JComboBox questionComboBox;
    private JLabel questionTypeLabel;
    private JLabel questionLabel;
    private JTextField questionTextField;
    private JLabel pointValueLabel;
    private JTextField pointValueTextField;
    private JButton submitButton;
    private JPanel titlePanel;
    private JLabel questionTitleLabel;
    private JButton saveQuestionButton;
    private JButton cancelButton;
    private JButton editAnswerButton;
    private JScrollPane answersScrollPane;
    private CourseworkMgmtController courseworkMgmtController;
    private Assignment assignment;
    private JFrame editQuestionFrame;
    private Question questionToEdit;
    private boolean isValidPointValue;
    private boolean isValidQuestion;

    public EditQuestionUI(CourseworkMgmtController courseworkMgmtController, Question questionToEdit){
        this.courseworkMgmtController = courseworkMgmtController;
        this.questionToEdit = questionToEdit;
        this.assignment = courseworkMgmtController.getEditAssignmentUI().getAssignment();
        editQuestionFrame = new JFrame("Question Builder");
        questionComboBox.addItem("Multiple Choice");
        questionComboBox.addItem("Open Ended");
        editQuestionFrame.setResizable(false);
        editQuestionFrame.setMinimumSize(new Dimension(700, 600));
        editQuestionFrame.setContentPane(questionViewPanel);
        editQuestionFrame.setLocationRelativeTo(null);
        this.editAnswerButton.setEnabled(false);
        this.saveQuestionButton.setEnabled(false);
        editQuestionFrame.setVisible(true);
        editQuestionFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.init();
    }

    private void init(){
        addALButtons();
        addFocusListenerTextField();
        setUpAnswerListData();
        isValidPointValue = true;
        isValidQuestion = true;
        questionTextField.setText(questionToEdit.getQuestion());
        pointValueTextField.setText(String.valueOf(questionToEdit.getQuestionPointWorth()));
        questionComboBox.setSelectedIndex(questionToEdit.getQuestionType() - 1);
        questionComboBox.setEnabled(false);
    }

    public void setUpAnswerListData(){
        DefaultListModel<Answer> model = new DefaultListModel<>();
        answerList.setModel(model);

        if (questionToEdit != null)
        {
            for (Answer answer : questionToEdit.getAnswerList().getAnswerList())
            {
                model.addElement(answer);
            }
        }
    }


    private void addALButtons(){
        editAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Answer answer = questionToEdit.getAnswerList().getAnswerList().get(answerList.getSelectedIndex());
                courseworkMgmtController.setEditAnswerUI(new EditAnswerUI(courseworkMgmtController, answer));
                editQuestionFrame.setEnabled(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseworkMgmtController.getEditAssignmentUI().getEditAssignmentFrame().setEnabled(true);
                editQuestionFrame.dispose();
                courseworkMgmtController.setEditQuestionUI(null);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkValidDoubleEntered(pointValueTextField.getText());
                if (isQuestionValid(assignment))
                {
                    editAnswerButton.setEnabled(true);
                    questionTextField.setEnabled(false);
                    pointValueTextField.setEnabled(false);
                    submitButton.setEnabled(false);
                    if (!questionToEdit.getAnswerList().getAnswerList().isEmpty() && checkIfAnyAnswersAreCorrect())
                    {
                        saveQuestionButton.setEnabled(true);
                    }
                }
            }
        });

        saveQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String question = questionTextField.getText();
                double pointValue = Double.parseDouble(pointValueTextField.getText());

                questionToEdit.setQuestion(question);
                questionToEdit.setPointValue(pointValue);

                courseworkMgmtController.getEditAssignmentUI().getEditAssignmentFrame().setEnabled(true);
                courseworkMgmtController.getEditAssignmentUI().checkAssignmentForQuestions();
                courseworkMgmtController.setEditQuestionUI(null);
                courseworkMgmtController.getEditAssignmentUI().setupQuestionListData();
                editQuestionFrame.dispose();
            }
        });
    }

    private void onWindowClosing()
    {
        courseworkMgmtController.getEditAssignmentUI().getEditAssignmentFrame().setEnabled(true);
        courseworkMgmtController.getEditAssignmentUI().getEditAssignmentFrame().transferFocus();
    }

    private boolean checkIfQuestionIsNew(Assignment assignment){
        for (Question question : assignment.getQuestionList().getQuestionList())
        {
            if (question.getQuestion().equalsIgnoreCase(questionTextField.getText()) && !questionTextField.getText().equals(questionToEdit.getQuestion()))
            {
                return false;
            }
        }
        return true;
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

    public boolean checkIfAnyAnswersAreCorrect()
    {
        for (Answer answer : questionToEdit.getAnswerList().getAnswerList())
        {
            if (answer.getIsCorrect())
            {
                return true;
            }
        }
        return false;
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

    private void addFocusListenerTextField(){
        editQuestionFrame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                super.windowGainedFocus(e);
                setUpAnswerListData();
            }
        });

        editQuestionFrame.addWindowListener(new WindowAdapter() {
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
                    editAnswerButton.setEnabled(false);
                }
                else if (questionTextField.getText().equals("Type your question here:") || questionTextField.getText().equals("This question already exists!"))
                {
                    questionTextField.setText("Type your question here:");
                    isValidQuestion = false;
                    editAnswerButton.setEnabled(false);
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
                    editAnswerButton.setEnabled(false);
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
                        editAnswerButton.setEnabled(false);
                    }
                }
            }
        });
    }

    public JTextField getQuestionTextField() {
        return questionTextField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JButton getSaveQuestionButton() {
        return saveQuestionButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public JFrame getEditQuestionFrame() {
        return editQuestionFrame;
    }
}
