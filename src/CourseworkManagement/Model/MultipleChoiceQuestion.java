package CourseworkManagement.Model;

public class MultipleChoiceQuestion extends Question{
    private String question;
    private double pointValue;
    private AnswerList answerList;
    public MultipleChoiceQuestion(String question, double pointValue)
    {
        this.question = question;
        this.pointValue = pointValue;
        this.answerList = new AnswerList();
    }
    @Override
    public String getQuestion() {
        return this.question;
    }

    @Override
    public Answer createAnswer(String answerDescription) {
        return new OpenEndedAnswer(answerDescription);
    }

    @Override
    public AnswerList getAnswerList()
    {
        return this.answerList;
    }

    @Override
    public String toString() {
        return this.question;
    }

    @Override
    public double getQuestionPointWorth() {
        return pointValue;
    }
}
