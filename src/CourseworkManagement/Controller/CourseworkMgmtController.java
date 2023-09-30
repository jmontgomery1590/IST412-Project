package CourseworkManagement.Controller;

import CourseManagement.Controller.CourseMgmtController;
import CourseManagement.Model.Course;
import CourseworkManagement.Model.Answer;
import CourseworkManagement.Model.Assignment;
import CourseworkManagement.Model.Question;
import CourseworkManagement.View.AnswerInterface;
import CourseworkManagement.View.AssignmentInterface;
import CourseworkManagement.View.QuestionInterface;
import StudentManagement.Student;

public class CourseworkMgmtController {
    private AssignmentInterface assignmentInterface; // changed this to private

    private CourseMgmtController courseMgmtController;
    private QuestionInterface questionInterface;
    private AnswerInterface answerInterface;
    private Course currentCourse;
    private Question currentQuestion;
    private Answer currentAnswer;
    private Assignment assignment;
    private Student student;

    /**
     * Constructor for the coursework management interface
     */
    public CourseworkMgmtController(CourseMgmtController courseMgmtController) {
        this.setCourseMgmtController(courseMgmtController);

        // initiates the interface
        this.setAssignmentInterface(new AssignmentInterface());
        this.setCurrentCourse(this.getCourseMgmtController().getCurrentCourse());

        // creates new assignment for the course page
        this.setAssignment(this.getAssignmentInterface().createNewAssignment());
        this.getCourseMgmtController().getCurrentPage().getLessonAssignments().add(this.getAssignment());

        // creating questions for the assignment
        this.setQuestionInterface(new QuestionInterface());
        this.setCurrentQuestion(this.getQuestionInterface().createAQuestion());

        // add possible answers to the question
        this.setAnswerInterface(new AnswerInterface());
        this.createAllPossibleAnswers();
    }

    public CourseworkMgmtController(CourseMgmtController courseMgmtControllerStudent, Student student){
        this.setCourseMgmtController(courseMgmtControllerStudent);
        this.setStudent(student);

        this.setAssignmentInterface(new AssignmentInterface());
        this.getAssignmentInterface().displayAssignments(this.getStudent().getEnrolledCourses()
                .get(0).getCoursePages().get(0).getLessonAssignments().get(0));
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

    public AssignmentInterface getAssignmentInterface() {
        return assignmentInterface;
    }

    public void setAssignmentInterface(AssignmentInterface assignmentInterface) {
        this.assignmentInterface = assignmentInterface;
    }

    public CourseMgmtController getCourseMgmtController() {
        return courseMgmtController;
    }

    public void setCourseMgmtController(CourseMgmtController courseMgmtController) {
        this.courseMgmtController = courseMgmtController;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
