package CourseworkManagement.Model;

public class OpenEndedQuestion implements AbstractQuestion{

    @Override
    public void ask() {
        System.out.println("This question would ask an open ended question (one that requires text entry)");
    }
}
