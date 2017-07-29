package icd9.classification;

import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class Section {

    private String code;
    private String title;
    private List<Subsection> subsections;

    public Section(String code, String title, List<Subsection> subsections) {
        this.code = code;
        this.title = title;
        this.subsections = subsections;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public List<Subsection> getSubsections() {
        return subsections;
    }
}
