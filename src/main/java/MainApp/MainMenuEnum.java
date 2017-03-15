package MainApp;

import Exceptions.WrongOptionsExeption;

import java.io.IOException;

/**
 * Created by moody on 02.03.17.
 */
public enum MainMenuEnum {


    EXIT(0, "Wyjście z programu"),
    ADD_SURVEY_PATIENT(1, "Dodaj kwestionariusz"),
    PRINT_SURVEY_PATIENT(2, "Wyświetl kwestionariusze"),
    FIND_BEST_TERM(3, "Znajdz termin")
    ;

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    MainMenuEnum(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    public static MainMenuEnum createFromInt(int option) {
        try {
            return MainMenuEnum.values()[option];
        }catch (ArrayIndexOutOfBoundsException e){
          //  System.err.println("Wprowadz poprwaną liczbę !!");
            throw new WrongOptionsExeption("Wprowadz poprawną opcję !");
        }

    }


}
