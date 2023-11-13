package CourseworkManagement.Controller;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import CourseworkManagement.Model.*;
import CourseworkManagement.View.AnswerUI;
import CourseworkManagement.View.AssignmentUI;
import CourseworkManagement.View.CourseworkMgmtUI;
import CourseworkManagement.View.QuestionUI;
import UserAuthentication.Model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this.courseworkMgmtUI = new CourseworkMgmtUI(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if (obj == this.courseworkMgmtUI.getNewAssignmentButton())
        {
            this.assignmentUI = new AssignmentUI(this);
            this.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(false);
        }

        if (this.assignmentUI != null)
        {
            if (obj == this.assignmentUI.getCancelButton())
            {
                this.getCourseMgmtController().getHomepageController().getHomepageUI().getHomeFrame().setEnabled(true);
                this.getAssignmentInterface().getAssignmentFrame().dispose();
                this.setAssignmentInterface(null);
            }
        }

        if (this.questionUI != null)
        {
            if (obj == this.questionUI.getCancelButton())
            {
                this.assignmentUI.getAssignmentFrame().setEnabled(true);
                this.questionUI.getQuestionFrame().dispose();
                this.questionUI = null;
            }

            else if (obj == this.questionUI.getSaveQuestionButton())
            {
                this.assignment.getQuestionList().addToList(this.question);
            }
        }
        if (this.answerUI != null)
        {
            if (obj == this.answerUI.getCancelButton())
            {
                this.questionUI.getQuestionFrame().setEnabled(true);
                this.answerUI.getAnswerFrame().dispose();
                this.answerUI = null;
            }
        }
    }

    private Question question;
    private QuestionList questionList;
    private CourseworkMgmtUI courseworkMgmtUI;
    private CourseMgmtController courseMgmtController;
    private QuestionUI questionUI;
    private AnswerUI answerUI;
    private Course currentCourse;
    private Assignment assignment;
    private User currentUser;
    private AssignmentTableModel assignmentTable;
    private AssignmentList assignmentList;
    private AssignmentUI assignmentUI;

    public QuestionUI getQuestionInterface() {
        return questionUI;
    }

    public void setQuestionInterface(QuestionUI questionUI) {
        this.questionUI = questionUI;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public CourseworkMgmtUI getCourseworkMgmtInterface() {
        return courseworkMgmtUI;
    }

    public AssignmentTableModel getAssignmentTable() {
        return assignmentTable;
    }

    public AssignmentList getAssignmentList() {
        return assignmentList;
    }

    public AssignmentUI getAssignmentInterface() {
        return assignmentUI;
    }

    public void setAssignmentInterface(AssignmentUI assignmentUI) {
        this.assignmentUI = assignmentUI;
    }

    public void setAnswerInterface(AnswerUI answerUI) {
        this.answerUI = answerUI;
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

    public Course getCurrentCourse() {
        return currentCourse;
    }
}
