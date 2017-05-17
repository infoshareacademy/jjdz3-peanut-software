package peanut.medicine;

import peanut.medicine.Exceptions.WrongOptionsExeption;

/**
 * Created by moody on 02.03.17.
 */
public enum MainMenuEnum {

    EXIT(0, "Wyjście z programu"),
    READ_DOCTORS_ICALS(1, "Wczytaj kalendarze lekarzy"),
    ADD_SURVEY_PATIENT(2, "Dodaj kwestionariusz"),
    PRINT_SURVEY_PATIENT(3, "Wyświetl kwestionariusze"),
    FIND_BEST_TERM(4, "Znajdz termin"),
    ICD_CLASSIFICATION(5,"Wczytaj klasyfikację");

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
            throw new WrongOptionsExeption("Wprowadź poprawną opcję!");
        }
    }
}
