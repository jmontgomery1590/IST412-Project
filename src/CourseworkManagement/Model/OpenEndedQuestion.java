package CourseworkManagement.Model;

public class OpenEndedQuestion extends Question{
    private String question;
    private String submittedAnswer = "";
    private double pointValue;
    private AnswerList answerList;
    private Boolean isCorrect;
    private int questionID;
    private int assignmentID;
    private int questionType;
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
    public void compareSubmittedAnswer() {
        isCorrect = this.submittedAnswer.equalsIgnoreCase(answerList.getAnswerList().get(0).toString());
    }


    @Override
    public String toString() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSubmittedAnswer() {
        return submittedAnswer;
    }

    public void setSubmittedAnswer(String submittedAnswer) {
        this.submittedAnswer = submittedAnswer;
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

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
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
    public String showSubmittedAnswer() {
        return submittedAnswer;
    }

    @Override
    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getAssignmentID() {
        return assignmentID;
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
