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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CourseworkMgmtController implements ActionListener {

    /**
     * Constructor for the coursework management interface
     * version to use when integrated into project
     */
    public CourseworkMgmtController(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
        this.currentCourse = this.courseMgmtController.getSelectedCourse();
        this.assignmentList = this.currentCourse.getAssignmentList();
        this.assignmentTable = new AssignmentTableModel(this.getAssignmentList().getAssignments());
        this.courseworkMgmtInterface = new CourseworkMgmtInterface(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if (obj == this.courseworkMgmtInterface.getNewAssignmentButton())
        {
            this.assignmentInterface = new AssignmentInterface(this);
            this.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
        }

        if (this.assignmentInterface != null)
        {
            if (obj == this.assignmentInterface.getCancelButton())
            {
                this.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                this.getAssignmentInterface().getAssignmentFrame().dispose();
                this.setAssignmentInterface(null);
            }
        }

        if (this.questionInterface != null)
        {
            if (obj == this.questionInterface.getCancelButton())
            {
                this.assignmentInterface.getAssignmentFrame().setEnabled(true);
                this.questionInterface.getQuestionFrame().dispose();
                this.questionInterface = null;
            }

            else if (obj == this.questionInterface.getSaveQuestionButton())
            {
                this.assignment.getQuestionList().addToList(this.question);
            }
        }
        if (this.answerInterface != null)
        {
            if (obj == this.answerInterface.getCancelButton())
            {
                this.questionInterface.getQuestionFrame().setEnabled(true);
                this.answerInterface.getAnswerFrame().dispose();
                this.answerInterface = null;
            }
        }
    }

    private Question question;
    private QuestionList questionList;
    private CourseworkMgmtInterface courseworkMgmtInterface;

    private CourseMgmtController courseMgmtController;
    private QuestionInterface questionInterface;
    private AnswerInterface answerInterface;
    private Course currentCourse;
    private Assignment assignment;
    private User currentUser;
    private AssignmentTableModel assignmentTable;
    private AssignmentList assignmentList;

    private AssignmentInterface assignmentInterface;


    public QuestionInterface getQuestionInterface() {
        return questionInterface;
    }

    public void setQuestionInterface(QuestionInterface questionInterface) {
        this.questionInterface = questionInterface;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
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

    public void setAnswerInterface(AnswerInterface answerInterface) {
        this.answerInterface = answerInterface;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public CourseMgmtController getCourseMgmtController() {
        return courseMgmtController;
    }

    public void setCourseMgmtController(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
    }

    public QuestionList getQuestionList() {
        return questionList;
    }

    public void setQuestionList(QuestionList questionList) {
        this.questionList = questionList;
    }

    public AnswerInterface getAnswerInterface() {
        return answerInterface;
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }
}
