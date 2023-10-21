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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CourseworkMgmtController implements ActionListener {

    /**
     * Test Coursework w/out being integrated
     */
    public CourseworkMgmtController(User user){
        this.setCurrentUser(user);
        this.setAssignmentList(new AssignmentList());
        this.setAssignmentTable(new AssignmentTableModel(this.getAssignmentList().getAssignments()));
        this.setCourseworkMgmtInterface(new CourseworkMgmtInterface(this));
        addALButtons();
    }
    /**
     * Constructor for the coursework management interface
     * version to use when integrated into project
     */
    public CourseworkMgmtController(CourseMgmtController courseMgmtController) {
        this.setCourseMgmtController(courseMgmtController);
        this.setCurrentUser(this.getCourseMgmtController().getCourseworkMgmtCntrl().getCurrentUser());
        this.setAssignmentList(new AssignmentList());
        this.setAssignmentTable(new AssignmentTableModel(this.getAssignmentList().getAssignments()));
        this.setCourseworkMgmtInterface(new CourseworkMgmtInterface(this));
    }

    public void addALButtons(){
        this.getCourseworkMgmtInterface().getNewAssignmentButton().addActionListener(this);
        this.getCourseworkMgmtInterface().getDeleteAssignmentButton().addActionListener(this);
        this.getCourseworkMgmtInterface().getEditAssignmentButton().addActionListener(this);
        this.getCourseworkMgmtInterface().getViewAssignmentButton().addActionListener(this);
    }

    public void addALAssignmentButtons(){
        this.getAssignmentInterface().getCancelButton().addActionListener(this);
        this.getAssignmentInterface().getCreateAssignmentButton().addActionListener(this);
        this.getAssignmentInterface().getAddQuestionButton().addActionListener(this);
    }

    public void addALQuestionButtons(){
        this.getQuestionInterface().getCancelButton().addActionListener(this);
        this.getQuestionInterface().getSaveQuestionButton().addActionListener(this);
        this.getQuestionInterface().getAddAnswerButton().addActionListener(this);
    }

    public void addALAnswerButtons(){
        this.getAnswerInterface().getSaveAnswerButton().addActionListener(this);
        this.getAnswerInterface().getCancelButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();

        if (obj == this.getCourseworkMgmtInterface().getNewAssignmentButton()){
            this.setAssignmentInterface(new AssignmentInterface(this));
            this.addALAssignmentButtons();
            this.getCourseworkMgmtInterface().getView().setEnabled(false);
        }

        else if (obj == this.getAssignmentInterface().getAddQuestionButton()){
            this.setQuestionInterface(new QuestionInterface(this));
            this.addALQuestionButtons();
            this.getAssignmentInterface().getAssignmentFrame().setEnabled(false);
        }

        else if (obj == this.getAssignmentInterface().getCancelButton()){
            this.getCourseworkMgmtInterface().getView().setEnabled(true);
            this.getAssignmentInterface().getAssignmentFrame().dispose();
        }

        else if (obj == this.getQuestionInterface().getCancelButton()){
            this.getAssignmentInterface().getAssignmentFrame().setEnabled(true);
            this.getQuestionInterface().getQuestionFrame().dispose();
        }

        else if (obj == this.getQuestionInterface().getAddAnswerButton()){
            this.setAnswerInterface(new AnswerInterface(this));
            this.addALAnswerButtons();
            this.getQuestionInterface().getQuestionFrame().setEnabled(false);
        }

        else if (obj == this.getAnswerInterface().getCancelButton()){
            this.getQuestionInterface().getQuestionFrame().setEnabled(true);
            this.getAnswerInterface().getAnswerFrame().dispose();
        }
    }



    private CourseworkMgmtInterface courseworkMgmtInterface;

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

    private AssignmentInterface assignmentInterface;

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

    public AssignmentInterface getAssignmentInterface() {
        return assignmentInterface;
    }

    public void setAssignmentInterface(AssignmentInterface assignmentInterface) {
        this.assignmentInterface = assignmentInterface;
    }
}
