/**
 * Created by moody on 20.02.17.
 */
public enum Option {


    EXIT(0, "Wyjście z programu"),
    ADD_SURVEY(1, "Wykonaj formularz dla pacjenta");


    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    Option(int value, String desc) {
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

