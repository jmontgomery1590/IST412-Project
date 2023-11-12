package CourseworkManagement.Model;

public abstract class AbstractFactory {
    public abstract Question createQuestion(String question, double pointValue);
    public abstract Answer createAnswer(String answer);
}
