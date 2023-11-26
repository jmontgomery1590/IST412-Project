package CourseworkManagement.Controller;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import CourseworkManagement.Model.*;
import CourseworkManagement.View.*;
import DatabaseMgmt.DatabaseConnection;
import UserAuthentication.Model.User;

public class CourseworkMgmtController {

    /**
     * Constructor for the coursework management interface
     * version to use when integrated into project
     */
    public CourseworkMgmtController(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
        this.currentCourse = this.courseMgmtController.getSelectedCourse();
        this.currentCourse.setAssignmentList(database.getAssignmentsByCourse(this));
        this.assignmentList = this.currentCourse.getAssignmentList();
        this.assignmentTable = new AssignmentTableModel(this.getAssignmentList().getAssignments());
        this.courseworkMgmtUI = new CourseworkMgmtUI(this);
    }

    private Question question;
    private QuestionList questionList;
    private CourseworkMgmtUI courseworkMgmtUI;

    private CourseMgmtController courseMgmtController;
    private AddQuestionUI addQuestionUI;
    private AddAnswerUI addAnswerUI;
    private Course currentCourse;
    private Assignment assignment;
    private User currentUser;
    private AssignmentTableModel assignmentTable;
    private AssignmentList assignmentList;

    private AddAssignmentUI addAssignmentUI;
    private ViewAssignmentUI viewAssignmentUI;
    private DatabaseConnection database = new DatabaseConnection();


    public AddQuestionUI getQuestionInterface() {
        return addQuestionUI;
    }

    public void setQuestionInterface(AddQuestionUI addQuestionUI) {
        this.addQuestionUI = addQuestionUI;
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

    public void setCourseworkMgmtInterface(CourseworkMgmtUI courseworkMgmtUI) {
        this.courseworkMgmtUI = courseworkMgmtUI;
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

    public AddAssignmentUI getAssignmentInterface() {
        return addAssignmentUI;
    }

    public void setAssignmentInterface(AddAssignmentUI addAssignmentUI) {
        this.addAssignmentUI = addAssignmentUI;
    }

    public void setAnswerInterface(AddAnswerUI addAnswerUI) {
        this.addAnswerUI = addAnswerUI;
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

    public AddAnswerUI getAnswerInterface() {
        return addAnswerUI;
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

    public ViewAssignmentUI getViewAssignmentUI() {
        return viewAssignmentUI;
    }

    public void setViewAssignmentUI(ViewAssignmentUI viewAssignmentUI) {
        this.viewAssignmentUI = viewAssignmentUI;
    }

    public DatabaseConnection getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseConnection database) {
        this.database = database;
    }
}