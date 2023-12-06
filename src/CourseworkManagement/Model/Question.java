package CourseworkManagement.Model;

import java.util.ArrayList;

public abstract class Question {
    public abstract String getQuestion();
    public abstract Answer createAnswer(String string);
    public abstract AnswerList getAnswerList();

    public abstract double getQuestionPointWorth();

    public abstract void compareSubmittedAnswer();

    public abstract int getQuestionID();

    public abstract void saveAnswer(String submittedAnswer);

    public abstract String showSubmittedAnswer();

    public abstract Boolean getIsCorrect();
    public abstract void setQuestionID(int questionid);

    public abstract int getQuestionType();

    public abstract void setQuestion(String string);
    public abstract void setPointValue(double pointValue);
}
