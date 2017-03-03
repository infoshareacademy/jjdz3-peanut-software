package Survey;

import jdk.nashorn.internal.runtime.options.Options;

/**
 * Created by moody on 24.02.17.
 */
public enum SurveyEnumOption {

    EXIT(0, "Powrót do głownego menu"),
    ADD_SURVEY_PATIENT(1, "Dodaj Kwestionariusz"),
    PRINT_SURVEY_PATIENT(2, "Wyświetl wprowadzone kwestionariusze"),
    PRINT_FIND_PATIENT_BY_LASTNAME(3, "Znajdz pacjenta : ");


    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    SurveyEnumOption(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    public static SurveyEnumOption createFromInt(int option) {
        return SurveyEnumOption.values()[option];
    }


}
