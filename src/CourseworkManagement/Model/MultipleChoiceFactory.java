package CourseworkManagement.Model;

public class MultipleChoiceFactory extends AbstractFactory {
    @Override
    public Question createQuestion(String question, double pointValue)
    {
        return new MultipleChoiceQuestion(question, pointValue);
    }

    @Override
    public Answer createAnswer(String answer)
    {
        return new MultipleChoiceAnswer(answer);
    }
}
