package CourseworkManagement.Model;

import java.util.ArrayList;

public class AnswerList {
    private Answer answer;
    private ArrayList<Answer> answerList = new ArrayList<>();
    public AnswerList()
    {
        if (this.answerList.isEmpty())
        {
            createAnswerList();
        }
    }

    public void createAnswerList()
    {
        for (int i = 1; i <= 4; i++)
        {
            this.answer = new MultipleChoiceAnswer("Test");
        }
    }

    public void addToList(Answer answer)
    {
        if (!this.answerList.contains(answer))
        {
            this.answerList.add(answer);
        }
    }

    @Override
    public String toString(){
        return answer.getAnswerDescription();
    }

    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Answer> answerList) {
        this.answerList = answerList;
    }
}
