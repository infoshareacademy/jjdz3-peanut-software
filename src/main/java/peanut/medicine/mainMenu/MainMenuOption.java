package peanut.medicine.mainMenu;

import peanut.medicine.exceptions.WrongOptionException;

/**
 * Created by moody on 02.03.17.
 */
public enum MainMenuOption {

    EXIT(0, "Wyjście z programu"),
    READ_DOCTORS_ICALS(1, "Wczytaj kalendarze lekarzy"),
    ADD_SURVEY_PATIENT(2, "Dodaj kwestionariusz"),
    PRINT_SURVEY_PATIENT(3, "Wyświetl kwestionariusze"),
    FIND_BEST_TERM(4, "Znajdz termin"),
    ICD_CLASSIFICATION(5, "Wczytaj klasyfikację");

    private int value;
    private String description;

    MainMenuOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    public static MainMenuOption createFromInt(int option) {
        try {
            return MainMenuOption.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongOptionException("Wprowadz poprawną opcję !");
        }
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }
}
