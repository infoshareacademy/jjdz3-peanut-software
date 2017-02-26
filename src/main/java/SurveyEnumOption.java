/**
 * Created by moody on 24.02.17.
 */
public enum SurveyEnumOption {

    EXIT(0, "Wyjście z programu"),
    ADD_PATIENT(1, "Dodaj pacjenta"),
    SHOW_PATIENT(2, "Wyświetl wprowadzonych pacjentów");


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

    public static Option createFromInt(int option) {
        return Option.values()[option];
    }


}
