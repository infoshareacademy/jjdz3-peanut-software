package peanut.medicine.mainMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by moody on 23.02.17.
 */
public class InputReader {

    private final static Logger LOGGER = LoggerFactory.getLogger(InputReader.class);

    private Scanner scanner;

    public InputReader() {
        scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

    public int getValueInt() {
        try {
            int number = scanner.nextInt();
            scanner.nextLine();
            LOGGER.debug(String.valueOf(number));
            return number;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return 99;
        }
    }

    public String getValueString(){
        System.out.println();
        String answer = scanner.nextLine();
        LOGGER.debug(answer);
        return answer ;
    }
}
