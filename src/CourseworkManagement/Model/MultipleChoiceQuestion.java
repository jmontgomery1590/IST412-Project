package CourseworkManagement.Model;

public class MultipleChoiceQuestion extends Question{
    private String question;
    private double pointValue;
    private AnswerList answerList;
    private Boolean isCorrect;
    private String submittedAnswer = "";
    private int questionID;
    private int assignmentID;
    private int questionType;

    public MultipleChoiceQuestion(String question, double pointValue)
    {
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

    @Override
    public void compareSubmittedAnswer() {
        for (Answer answer : answerList.getAnswerList())
        {
            if (answer.getAnswer().equalsIgnoreCase(submittedAnswer) && answer.getIsCorrect())
            {
                isCorrect = true;
            }
        }
    }

    @Override
    public String showSubmittedAnswer() {
        return submittedAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public double getPointValue() {
        return pointValue;
    }

    public void setPointValue(double pointValue) {
        this.pointValue = pointValue;
    }

    public void setAnswerList(AnswerList answerList) {
        this.answerList = answerList;
    }

    @Override
    public int getQuestionID() {
        return questionID;
    }

    @Override
    public void saveAnswer(String submittedAnswer) {
        this.submittedAnswer = submittedAnswer;
    }

    @Override
    public Boolean getIsCorrect() {
        return isCorrect;
    }

    @Override
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }
}
