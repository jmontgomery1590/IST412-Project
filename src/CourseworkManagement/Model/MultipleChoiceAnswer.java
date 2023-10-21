package CourseworkManagement.Model;

public class MultipleChoiceAnswer implements AbstractAnswer{

    @Override
    public void respond() {
        System.out.println("You would select your answer(s) and NOT type it.");
    }
}
