package CourseworkManagement.Model;

public class OpenEndedAnswer extends Answer {
    private String answer;
    private Boolean isCorrect;
    public OpenEndedAnswer(String answer)
    {
        this.answer = answer;
        this.isCorrect = false;
    }

    @Override
    public String getAnswer()
    {
        return this.answer;
    }

    @Override
    public String getAnswerDescription() {
        return this.answer;
    }

    @Override
    public String toString() {
        return this.answer;
    }

    @Override
    public void markCorrect()
    {
        this.isCorrect = true;
    }

    @Override
    public void markIncorrect()
    {
        this.isCorrect = false;
    }

    @Override
    public boolean getIsCorrect() {
        return this.isCorrect;
    }
}
