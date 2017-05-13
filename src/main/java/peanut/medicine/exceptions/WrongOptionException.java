package peanut.medicine.exceptions;

/**
 * Created by moody on 02.03.17.
 */
public class WrongOptionException extends RuntimeException {

    public WrongOptionException(String wrongOptionException){
        super(wrongOptionException);
    }
}
