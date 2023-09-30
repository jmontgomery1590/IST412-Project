package CourseworkManagement.Model;

public class Answer {
    private String answerDescription;
    private boolean answerCorrect;

    public Answer(String answerDescription, boolean answerCorrect){
        this.setAnswerDescription(answerDescription);
        this.setAnswerCorrect(answerCorrect);
    }


    public String getAnswerDescription() {
        return answerDescription;
    }

    public void setAnswerDescription(String answerDescription) {
        this.answerDescription = answerDescription;
    }

    public boolean isAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(boolean answerCorrect) {
        this.answerCorrect = answerCorrect;
    }
}
