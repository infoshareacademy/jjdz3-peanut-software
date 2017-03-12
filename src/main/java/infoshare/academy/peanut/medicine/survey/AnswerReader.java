package infoshare.academy.peanut.medicine.survey;

import java.util.Scanner;

/**
 * Created by moody on 23.02.17.
 */
public class AnswerReader {


    private Scanner scanner;

    public AnswerReader() {
        scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

    public int getValueInt() {
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public String getValueString(){
        System.out.println();
        String answer = scanner.nextLine();

        return answer ;

    }
}
