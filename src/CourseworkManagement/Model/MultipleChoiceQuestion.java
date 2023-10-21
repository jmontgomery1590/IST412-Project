package CourseworkManagement.Model;

import java.util.ArrayList;

public class MultipleChoiceQuestion implements AbstractQuestion{
    @Override
    public void ask() {
        System.out.println("This will print out a lot options for you to choose from");
    }
}
