package CourseworkManagement.Model;

import java.util.ArrayList;

public class QuestionList {

    private ArrayList<Question> questionList;
    public QuestionList()
    {
        this.questionList = new ArrayList<>();
    }

    public void addToList(Question question)
    {
        if (!this.questionList.contains(question))
        {
            this.questionList.add(question);
        }
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }
}
