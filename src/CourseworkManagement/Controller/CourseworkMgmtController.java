package CourseworkManagement.Controller;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import CourseworkManagement.Model.*;
import CourseworkManagement.View.*;
import DatabaseMgmt.DatabaseConnection;
import UserAuthentication.Model.Student;
import UserAuthentication.Model.User;

import java.util.Objects;

public class CourseworkMgmtController {

    /**
     * Constructor for the coursework management interface
     * version to use when integrated into project
     */
    public CourseworkMgmtController(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
        this.currentUser = this.courseMgmtController.getHomepageController().getUser();
        this.currentCourse = this.courseMgmtController.getSelectedCourse();
        this.database.getStudentListForCourse(currentCourse);
        this.loadAssignmentByRole();
        this.courseworkMgmtUI = new CourseworkMgmtUI(this);
    }

    public void loadAssignmentByRole()
    {
        if (Objects.equals(currentUser.getRoleID(), "4"))
        {
            loadStudentAssignmentList();
        }
        else
        {
            loadCourseAssignmentList();
        }
    }

    public void loadCourseAssignmentList(){
        this.currentCourse.setAssignmentList(database.getAssignmentsByCourseAlone(this));
        this.assignmentList = this.currentCourse.getAssignmentList();
        this.courseAssignmentTableModel = new CourseAssignmentTableModel(this.assignmentList.getAssignments());
    }

    public void loadStudentAssignmentList(){
        currentStudent = new Student(currentUser.getUserName(), currentUser.getPassword());
        currentStudent.setUserIDNumber(currentUser.getUserIDNumber());
        currentStudent.setFirstName(currentUser.getFirstName());
        currentStudent.setLastName(currentUser.getLastName());
        currentStudent.setRoleID(currentUser.getRoleID());
        this.currentCourse.setAssignmentList(database.getStudentAssignmentByCourse(this));
        this.assignmentList = this.currentCourse.getAssignmentList();
        this.studentAssignmentTableModel = new StudentAssignmentTableModel(this.assignmentList.getAssignments());
    }

    public void loadAllStudentsAssignmentList(){
        this.currentCourse.setAssignmentList(database.getAllStudentAssignmentByCourse(this, currentCourse.getStudentsEnrolled()));
        this.assignmentList = this.currentCourse.getAssignmentList();
        for (Assignment assignmentToGrade : assignmentList.getAssignments())
        {
            assignmentToGrade.gradeAssignment();
            if (assignmentToGrade.isCompleted()){
                database.updateAssignmentPointsEarnedAndGrade(assignmentToGrade);
            }
        }
        this.assignmentByStudentTablemodel = new AssignmentByStudentTablemodel(this.assignmentList.getAssignments());
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
    private StudentAssignmentTableModel studentAssignmentTableModel;
    private AssignmentByStudentTablemodel assignmentByStudentTablemodel;
    private AssignmentList assignmentList = new AssignmentList();
    private AddAssignmentUI addAssignmentUI;
    private ViewAssignmentUI viewAssignmentUI;
    private DatabaseConnection database = new DatabaseConnection();
    private Student currentStudent;
    private CourseAssignmentTableModel courseAssignmentTableModel;
    private EditAssignmentUI editAssignmentUI;
    private EditQuestionUI editQuestionUI;
    private EditAnswerUI editAnswerUI;

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

    public User getCurrentUser() {
        return currentUser;
    }

    public StudentAssignmentTableModel getStudentAssignmentTableModel() {
        return studentAssignmentTableModel;
    }

    public AssignmentList getAssignmentList() {
        return assignmentList;
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

    public Course getCurrentCourse() {
        return currentCourse;
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

    public AssignmentByStudentTablemodel getAssignmentByStudentTablemodel() {
        return assignmentByStudentTablemodel;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public CourseAssignmentTableModel getCourseAssignmentTableModel() {
        return courseAssignmentTableModel;
    }

    public EditAssignmentUI getEditAssignmentUI() {
        return editAssignmentUI;
    }

    public void setEditAssignmentUI(EditAssignmentUI editAssignmentUI) {
        this.editAssignmentUI = editAssignmentUI;
    }

    public EditQuestionUI getEditQuestionUI() {
        return editQuestionUI;
    }

    public void setEditQuestionUI(EditQuestionUI editQuestionUI) {
        this.editQuestionUI = editQuestionUI;
    }

    public void setEditAnswerUI(EditAnswerUI editAnswerUI) {
        this.editAnswerUI = editAnswerUI;
    }
}