package CourseworkManagement.Model;

public interface AssignmentFactory {
    abstract AbstractQuestion createQuestion();
    abstract AbstractAnswer createAnswer();
}
