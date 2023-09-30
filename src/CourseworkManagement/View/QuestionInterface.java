package CourseworkManagement.View;

import CourseworkManagement.Model.Question;

import java.sql.SQLOutput;
import java.util.Scanner;

public class QuestionInterface {
    public QuestionInterface(){
    }

    public Question createAQuestion(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the question:");
        String question = scanner.nextLine();
        System.out.println("How many points is this question worth:");
        double pointValue = 0.0;
        while (scanner.hasNext()){
            if (!scanner.hasNextDouble()){
                System.out.println("Invalid Point Value. Please try again:");
                scanner.next();
            } else {
                pointValue = scanner.nextDouble();
                break;
            }
        }

        return new Question(question, pointValue);
    }
}
