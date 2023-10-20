package CourseworkManagement.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Assignment {
    private String assignmentTitle;
    private ArrayList<Question> assignmentQuestions;
    private double possibleScore;
    private double earnedScore;

    private String grade;
    private boolean completed;

    public Assignment(String assignmentTitle){
        this.setAssignmentTitle(assignmentTitle);
        this.setAssignmentQuestions(new ArrayList<>());
        this.setPossibleScore(0.0);
        this.setEarnedScore(0.0);
        this.setCompleted(false);
        this.setGrade("--");
    }

    /**
     * Grade assignment and post grade
     */
    public void gradeAssignment() {
        double num = (this.getEarnedScore() / this.getPossibleScore());
        DecimalFormat df = new DecimalFormat("#.#");
        String roundedGrade = df.format(num);
        this.setGrade(roundedGrade+ "%");
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public ArrayList<Question> getAssignmentQuestions() {
        return assignmentQuestions;
    }

    public void setAssignmentQuestions(ArrayList<Question> assignmentQuestions) {
        this.assignmentQuestions = assignmentQuestions;
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
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
