package CourseworkManagement.Model;

public class MultipleChoiceAnswer extends Answer {
    private String answer;
    private Boolean isCorrect;
    private Boolean isSelected;
    public MultipleChoiceAnswer(String answer)
    {
        this.answer = answer;
        this.isCorrect = false;
        this.isSelected = false;
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
}
