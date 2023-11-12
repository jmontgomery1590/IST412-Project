package CourseworkManagement.Model;

public class OpenEndedQuestion extends Question{
    private String question;
    private String submittedAnswer;
    private double pointValue;
    private OpenEndedAnswer answer;
    private AnswerList answerList;
    public OpenEndedQuestion(String question, double pointValue){
        this.question = question;
        this.pointValue = pointValue;
        this.answerList = new AnswerList();
    }
    public void addAnswer(OpenEndedAnswer correctAnswer)
    {
        this.answer = correctAnswer;
    }

    public void checkAnswer(String submittedAnswer)
    {
        if (this.answer.getAnswer().equalsIgnoreCase(submittedAnswer))
        {
            this.answer.setCorrect(true);
        }
    }
    @Override
    public String getQuestion() {
        return null;
    }

    @Override
    public Answer createAnswer(String answerDescription) {
        return new OpenEndedAnswer(answerDescription);
    }

    @Override
    public AnswerList getAnswerList() {
        return this.answerList;
    }

    @Override
    public double getQuestionPointWorth() {
        return pointValue;
    }


    @Override
    public String toString() {
        return this.question;
    }
}
