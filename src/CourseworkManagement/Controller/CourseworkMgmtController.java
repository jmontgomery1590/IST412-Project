package CourseworkManagement.Controller;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import CourseworkManagement.Model.*;
import CourseworkManagement.View.AnswerInterface;
import CourseworkManagement.View.AssignmentInterface;
import CourseworkManagement.View.CourseworkMgmtInterface;
import CourseworkManagement.View.QuestionInterface;
import StudentManagement.Student;
import UserAuthentication.Model.User;

public class CourseworkMgmtController {
    private CourseworkMgmtInterface courseworkMgmtInterface; // changed this to private

    private CourseMgmtController courseMgmtController;
    private QuestionInterface questionInterface;
    private AnswerInterface answerInterface;
    private Course currentCourse;
    private Question currentQuestion;
    private Answer currentAnswer;
    private Assignment assignment;
    private User currentUser;
    private AssignmentTableModel assignmentTable;
    private AssignmentList assignmentList;

    /**
     * Constructor for the coursework management interface
     */
    public CourseworkMgmtController(CourseMgmtController courseMgmtController) {
        this.setCourseMgmtController(courseMgmtController);
        this.setCurrentUser(this.getCourseMgmtController().getCourseworkMgmtCntrl().getCurrentUser());
        this.setAssignmentList(new AssignmentList());
        this.setAssignmentTable(new AssignmentTableModel(this.getAssignmentList().getAssignments()));
        this.setCourseworkMgmtInterface(new CourseworkMgmtInterface(this));
    }

    private void createAllPossibleAnswers(){
        int totalAnswers = this.getAnswerInterface().totalPossibleAnswers();
        while (totalAnswers > 0){
            this.getCurrentQuestion().addPossibleAnswer(this.getAnswerInterface().createPossibleAnswer());
            totalAnswers -= 1;
        }
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

    public QuestionInterface getQuestionInterface() {
        return questionInterface;
    }

    public void setQuestionInterface(QuestionInterface questionInterface) {
        this.questionInterface = questionInterface;
    }

    public AnswerInterface getAnswerInterface() {
        return answerInterface;
    }

    public void setAnswerInterface(AnswerInterface answerInterface) {
        this.answerInterface = answerInterface;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Answer getCurrentAnswer() {
        return currentAnswer;
    }

    public void setCurrentAnswer(Answer currentAnswer) {
        this.currentAnswer = currentAnswer;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public CourseworkMgmtInterface getAssignmentInterface() {
        return courseworkMgmtInterface;
    }

    public void setAssignmentInterface(CourseworkMgmtInterface courseworkMgmtInterface) {
        this.courseworkMgmtInterface = courseworkMgmtInterface;
    }

    public CourseMgmtController getCourseMgmtController() {
        return courseMgmtController;
    }

    public void setCourseMgmtController(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
    }

    public CourseworkMgmtInterface getCourseworkMgmtInterface() {
        return courseworkMgmtInterface;
    }

    public void setCourseworkMgmtInterface(CourseworkMgmtInterface courseworkMgmtInterface) {
        this.courseworkMgmtInterface = courseworkMgmtInterface;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public AssignmentTableModel getAssignmentTable() {
        return assignmentTable;
    }

    public void setAssignmentTable(AssignmentTableModel assignmentTable) {
        this.assignmentTable = assignmentTable;
    }

    public AssignmentList getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(AssignmentList assignmentList) {
        this.assignmentList = assignmentList;
    }
}
