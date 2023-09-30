package CourseworkManagement.View;

import CourseworkManagement.Model.Answer;

import java.util.Scanner;

public class AnswerInterface {
    public AnswerInterface(){

    }

    public Answer createPossibleAnswer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input a Possible Answer:");
        String answerDesc = scanner.nextLine();
        System.out.println("Is this a correct answer?: (Y/N)");
        boolean correctAnswer = false;
        while (scanner.hasNext()){
            String checkResponse = scanner.nextLine();
            if (checkResponse.equalsIgnoreCase("Y") || checkResponse.equalsIgnoreCase("YES")){
                correctAnswer = true;
                break;
            } else if (checkResponse.equalsIgnoreCase("N") || checkResponse.equalsIgnoreCase("NO")){
                break;
            } else {
                System.out.println("Please enter a valid response (yes or no):");
            }
        }
        return new Answer(answerDesc, correctAnswer);
    }

    public int totalPossibleAnswers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many possible answers for this question?");
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
        return max;
    }
}
