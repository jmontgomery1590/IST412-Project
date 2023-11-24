package CourseworkManagement.Model;

public abstract class Answer {
    public abstract String getAnswer();
    public abstract String getAnswerDescription();

    public abstract void markCorrect();
    public abstract void markIncorrect();

    public abstract boolean getIsCorrect();
}
