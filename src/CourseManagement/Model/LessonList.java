package CourseManagement.Model;

import java.util.ArrayList;

public class LessonList {
    public Lesson lesson;
    private ArrayList<Lesson> lessons = new ArrayList<>();

    public LessonList() {}

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }
}
