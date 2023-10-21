package CourseworkManagement.Model;

public class OpenEndedFactory implements AssignmentFactory{
    @Override
    public AbstractQuestion createQuestion() {
        return new OpenEndedQuestion();
    }

    @Override
    public AbstractAnswer createAnswer() {
        return new OpenEndedAnswer();
    }
}
