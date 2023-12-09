package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class ViewAssignmentUI {

    public ViewAssignmentUI(CourseworkMgmtController courseworkMgmtController){
        this.courseworkMgmtController = courseworkMgmtController;
        questionNumber = 0;
        setQuestions(0);
        addALButtons();
        verifyButtonAccess();
        updateAssignmentLabel();
        checkNextAndPrevButtons();
    }

    public void verifyButtonAccess()
    {
        if (courseworkMgmtController.getCurrentUser().getRoleID().equals("4"))
        {
            if (courseworkMgmtController.getAssignment().isCompleted())
            {
                submitButton.setEnabled(false);
                answerPanel.setEnabled(false);
                answerSubmittedLabel.setVisible(true);
                submittedAnswerLabel.setVisible(true);
                trueAnswerLabel.setVisible(true);
                correctAnswerLabel.setVisible(true);
            }
        }
        else
        {
            submitButton.setEnabled(false);
            answerPanel.setEnabled(false);
            answerSubmittedLabel.setVisible(true);
            submittedAnswerLabel.setVisible(true);
            trueAnswerLabel.setVisible(true);
            correctAnswerLabel.setVisible(true);
        }
    }

    public void loadAnswerComparison()
    {
        answerSubmittedLabel.setText(currentQuestion.showSubmittedAnswer());
        for (Answer answer : currentQuestion.getAnswerList().getAnswerList())
        {
            if (answer.getIsCorrect())
            {
                trueAnswerLabel.setText(answer.getAnswer());
            }
        }
    }

    public void fillMultiChoiceAnswerPanel(Question question){
        answerPanel.removeAll();
        answerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        answerGroup = new ButtonGroup();

        for (Answer option : question.getAnswerList().getAnswerList())
        {
            JRadioButton answerOption = new JRadioButton(option.getAnswer());
            answerOption.setFont(new Font("Arial", Font.BOLD, 20));
            answerOption.setBackground(Color.getColor("B3B5C2"));
            answerOption.setForeground(Color.white);
            answerGroup.add(answerOption);
            answerPanel.add(answerOption, gbc);

            if (courseworkMgmtController.getAssignment().isCompleted())
            {
                answerOption.setEnabled(false);
            }
        }
        if (!currentQuestion.showSubmittedAnswer().isEmpty())
        {
            showSelectedAnswer();
        }
        answerPanel.revalidate();
        answerPanel.repaint();
    }

    public void fillOpenEndedAnswerPanel()
    {
        answerPanel.removeAll();
        answerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        typedAnswer.setText(currentQuestion.showSubmittedAnswer());
        JLabel answerLabel = new JLabel();
        answerLabel.setText("Your Answer: ");
        answerLabel.setFont(new Font("Arial",Font.BOLD, 20));
        answerLabel.setForeground(Color.white);
        typedAnswer.setSize(100, 10);
        answerLabel.setLabelFor(typedAnswer);
        answerPanel.add(answerLabel, gbc);
        answerPanel.add(typedAnswer, gbc);

        if (courseworkMgmtController.getAssignment().isCompleted())
        {
            typedAnswer.setEnabled(false);
        }

        answerPanel.revalidate();
        answerPanel.repaint();
    }

    public void updateQuestionLabel()
    {
        questionLabel.setText(currentQuestion.getQuestion());
    }

    public void updateAssignmentLabel()
    {
        assignmentTitleLabel.setText(courseworkMgmtController.getAssignment().getAssignmentTitle());
    }

    public void setQuestions(int questionListNumber){
        questionNumber = questionListNumber;
        questionList = courseworkMgmtController.getAssignment().getQuestionList();
        currentQuestion = questionList.getQuestionList().get(questionNumber);
        loadAnswerComparison();

        if (questionListNumber > 0)
        {
            prevQuestion = questionList.getQuestionList().get(questionListNumber - 1);
        }
        else
        {
            prevQuestion = null;
        }


        if (questionListNumber + 1 < questionList.getQuestionList().size())
        {
            nextQuestion = questionList.getQuestionList().get(questionListNumber + 1);
        }
        else
        {
            nextQuestion = null;
        }

        updateQuestionLabel();
        if (currentQuestion.getClass().equals(MultipleChoiceQuestion.class))
        {
            fillMultiChoiceAnswerPanel(currentQuestion);
        }
        else
        {
            fillOpenEndedAnswerPanel();
        }
    }

    public void checkNextAndPrevButtons(){
        previousButton.setEnabled(prevQuestion != null);

        nextButton.setEnabled(nextQuestion != null);
    }

    public void getSelectedAnswer(){
        if (currentQuestion.getClass().equals(MultipleChoiceQuestion.class))
        {
            for (Enumeration<AbstractButton> buttons = answerGroup.getElements(); buttons.hasMoreElements();)
            {
                AbstractButton button = buttons.nextElement();

                if (button.isSelected())
                {
                    currentQuestion.saveAnswer(button.getText());
                }
            }
        }
        else
        {
            currentQuestion.saveAnswer(typedAnswer.getText());
        }
    }

    public void showSelectedAnswer(){
        if (currentQuestion.getClass().equals(MultipleChoiceQuestion.class))
        {
            for (Enumeration<AbstractButton> buttons = answerGroup.getElements(); buttons.hasMoreElements();)
            {
                AbstractButton button = buttons.nextElement();

                if (button.getText().equalsIgnoreCase(currentQuestion.showSubmittedAnswer()))
                {
                    button.setSelected(true);
                }
            }
        }
    }

    public void addALButtons(){
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nextQuestion != null)
                {
                    getSelectedAnswer();
                    setQuestions(questionNumber + 1);
                    checkNextAndPrevButtons();
                }
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prevQuestion != null)
                {
                    getSelectedAnswer();
                    setQuestions(questionNumber - 1);
                    checkNextAndPrevButtons();
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSelectedAnswer();
                courseworkMgmtController.getDatabase().submitStudentAnswersToAssignmentQuestions(courseworkMgmtController.getAssignment());

                courseworkMgmtController.getCourseMgmtController().getHomepageController().getHomepageUI().getCardSwapper().show(courseworkMgmtController.getCourseMgmtController().getHomepageController().getHomepageUI().getViewPanel(), "Course Work");
                courseworkMgmtController.getCourseMgmtController().getHomepageController().getHomepageUI().getViewPanel().revalidate();
                courseworkMgmtController.getCourseMgmtController().getHomepageController().getHomepageUI().getViewPanel().repaint();
            }
        });
    }

    private JPanel readPanel;
    private JButton nextButton;
    private JButton previousButton;
    private JButton backButton;
    private JPanel buttonPanel;
    private JButton submitButton;
    private JLabel questionLabel;
    private JLabel assignmentTitleLabel;
    private JPanel answerPanel;
    private JPanel questionPanel;
    private JPanel titlePanel;
    private JLabel correctAnswerLabel;
    private JLabel submittedAnswerLabel;
    private JLabel trueAnswerLabel;
    private JLabel answerSubmittedLabel;

    private CourseworkMgmtController courseworkMgmtController;
    private Question prevQuestion;
    private Question currentQuestion;
    private Question nextQuestion;
    private int questionNumber;
    private JTextField typedAnswer = new JTextField();
    private ButtonGroup answerGroup;
    private QuestionList questionList;

    public JPanel getReadPanel() {
        return readPanel;
    }

    public void setReadPanel(JPanel readPanel) {
        this.readPanel = readPanel;
    }
}