package CourseManagement.View;

import CourseManagement.Model.Course;

import java.util.Scanner;

public class CourseMgmtInterface {
    public CourseMgmtInterface() {}

    /**
     * Displays the chosen course interface
     */
    public Course createCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Course ID#:");
        String courseID = scanner.nextLine();
        System.out.println("Course Name:");
        String courseName = scanner.nextLine();
        System.out.println("Max Enrolled:");
        int max = 0;
        while (scanner.hasNext()){
            if (!scanner.hasNextInt()){
                System.out.println("Please input a valid integer.");
                scanner.next();
            } else {
                max = scanner.nextInt();
                break;
            }
        }
        return new Course(courseID, courseName, max);
    }
}
