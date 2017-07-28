package icd9.classification;

/**
 * @author Mariusz Szymanski
 */
public class DetailedCategory {

    private String code;
    private String description;
    private String notes;

    public DetailedCategory(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
