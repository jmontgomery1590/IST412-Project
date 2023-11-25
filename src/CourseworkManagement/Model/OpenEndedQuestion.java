package CourseworkManagement.Model;

public class OpenEndedQuestion extends Question{
    private String question;
    private String submittedAnswer;
    private double pointValue;
    private AnswerList answerList;
    private Boolean isCorrect;
    public OpenEndedQuestion(String question, double pointValue){
        this.question = question;
        this.pointValue = pointValue;
        this.answerList = new AnswerList();
        this.isCorrect = false;
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
    public AnswerList getAnswerList() {
        return this.answerList;
    }

    @Override
    public double getQuestionPointWorth() {
        return pointValue;
    }

    @Override
    public void compareSubmittedAnswer(String submittedAnswer) {
        this.submittedAnswer = submittedAnswer;
        isCorrect = this.submittedAnswer.equalsIgnoreCase(answerList.getAnswerList().get(0).toString());
    }


    @Override
    public String toString() {
        return this.question;
    }
}
