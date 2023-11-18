package CourseworkManagement.View;

import CourseworkManagement.Controller.CourseworkMgmtController;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Question;
import CourseworkManagement.Model.QuestionList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAssignmentUI {

    public ViewAssignmentUI(CourseworkMgmtController courseworkMgmtController){
        this.courseworkMgmtController = courseworkMgmtController;
        questionNumber = 0;
        setQuestions(0);
        addALButtons();
        updateAssignmentLabel();
        checkNextAndPrevButtons();
    }

    public void fillAnswerPanel(Question question){
        answerPanel.removeAll();
        answerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        ButtonGroup group = new ButtonGroup();

        for (Answer option : question.getAnswerList().getAnswerList())
        {
            JRadioButton answerOption = new JRadioButton(option.getAnswer());
            answerOption.setFont(new Font("Arial", Font.BOLD, 20));
            group.add(answerOption);
            answerPanel.add(answerOption, gbc);
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
        fillAnswerPanel(currentQuestion);
    }

    public void checkNextAndPrevButtons(){
        previousButton.setEnabled(prevQuestion != null);

        nextButton.setEnabled(nextQuestion != null);
    }

    public void addALButtons(){
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nextQuestion != null)
                {
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
                    setQuestions(questionNumber - 1);
                    checkNextAndPrevButtons();
                }
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

    private CourseworkMgmtController courseworkMgmtController;
    private Question prevQuestion;
    private Question currentQuestion;
    private Question nextQuestion;
    private int questionNumber;
    private QuestionList questionList;

    public JPanel getReadPanel() {
        return readPanel;
    }

    public void setReadPanel(JPanel readPanel) {
        this.readPanel = readPanel;
    }
}
