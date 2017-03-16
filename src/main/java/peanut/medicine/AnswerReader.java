package peanut.medicine;

import java.util.InputMismatchException;
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
        try {
            int number = scanner.nextInt();
            scanner.nextLine();
            return number;
        } catch (InputMismatchException e)
        {
            scanner.nextLine();
            return 0;
        }
    }

    public String getValueString(){
        System.out.println();
        String answer = scanner.nextLine();

        return answer ;

    }
}
