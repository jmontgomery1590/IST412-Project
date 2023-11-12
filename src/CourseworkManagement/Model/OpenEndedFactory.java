package CourseworkManagement.Model;

public class OpenEndedFactory extends AbstractFactory {
    @Override
    public Question createQuestion(String question, double pointValue) {
        return new OpenEndedQuestion(question, pointValue);
    }

    @Override
    public Answer createAnswer(String answer) {
        return new OpenEndedAnswer(answer);
    }
}
