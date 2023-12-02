package CourseworkManagement.Model;

import java.util.ArrayList;

public abstract class Question {
    public abstract String getQuestion();
    public abstract Answer createAnswer(String string);
    public abstract AnswerList getAnswerList();

    public abstract double getQuestionPointWorth();

    public abstract void compareSubmittedAnswer(String selectedAnswer);

    public abstract int getQuestionID();
}
