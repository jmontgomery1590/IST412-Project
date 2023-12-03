package CourseworkManagement.Model;

import UserAuthentication.Model.Student;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Assignment {
    private String assignmentTitle;
    private QuestionList questionList = new QuestionList();
    private double possibleScore;
    private double earnedScore;
    private int assignmentID;

    private String grade;
    private boolean completed;
    private Student assignedStudent;

    public Assignment(String assignmentTitle){
        this.setAssignmentTitle(assignmentTitle);
        this.setCompleted(false);
    }

    /**
     * Grade assignment and post grade
     */
    public void gradeAssignment() {
        updateEarnedScore();
        double num = ((this.getEarnedScore() / this.getPossibleScore()) * 100);
        DecimalFormat df = new DecimalFormat("###.#");
        String roundedGrade = df.format(num);
        this.setGrade(roundedGrade);
    }

    public void updatePossibleScore()
    {
        this.possibleScore = 0;
        for (Question question : questionList.getQuestionList())
        {
            this.possibleScore += question.getQuestionPointWorth();
        }
    }

    public void updateEarnedScore() {
        for (Question question : questionList.getQuestionList())
        {
            if (question.getIsCorrect())
            {
                this.earnedScore += question.getQuestionPointWorth();
            }
        }
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }



    /**
     * Returns the total possible score for this assignment
     * @return Double representation of the total possible score for this assignment
     */
    public double getPossibleScore() {
        return possibleScore;
    }

    /**
     * Sets total possible score for this assignment
     * @param totalScore Sets the total possible score for this assignment
     */
    public void setPossibleScore(double totalScore) {
        this.possibleScore = totalScore;
    }

    /**
     * Returns the total earned score for this assignment
     * @return Double representation of the total earned score for this assignment
     */
    public double getEarnedScore() {
        return earnedScore;
    }

    /**
     * Sets the total earned score for this assignment
     * @param userScore Sets the total earned score for this assignment
     */
    public void setEarnedScore(double userScore) {
        this.earnedScore = userScore;
    }

    public String getGrade() {
        if (!completed)
        {
            return "--";
        }
        else
        {
            return grade;
        }
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public QuestionList getQuestionList() {
        return questionList;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setQuestionList(QuestionList questionList) {
        this.questionList = questionList;
        updatePossibleScore();
    }

    public Student getAssignedStudent() {
        return assignedStudent;
    }

    public void setAssignedStudent(Student assignedStudent) {
        this.assignedStudent = assignedStudent;
    }
}
