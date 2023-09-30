package CourseworkManagement.Model;

import java.util.ArrayList;

public class Question {
    private String desc;
    private String answer;
    private ArrayList<Answer> possibleAnswers;
    private String response;
    private double pointValue;

    public Question(String desc, double pointValue){
        this.setDesc(desc);
        this.setPointValue(pointValue);
        this.setPossibleAnswers(new ArrayList<>());
    }

    public void addPossibleAnswer(Answer answer){
        this.getPossibleAnswers().add(answer);
    }

    /**
     * Returns the user response for this assignment
     * @return String representation of the user response for this assignment
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * Compares user response against the answer to check for correct answer
     * @param userResponse User response given for this question
     * @param Answer Actual answer for this question
     */
    public void checkResponse(String userResponse, String Answer) {}

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<Answer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(ArrayList<Answer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public double getPointValue() {
        return pointValue;
    }

    public void setPointValue(double pointValue) {
        this.pointValue = pointValue;
    }
}
