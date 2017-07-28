package icd9.classification;

import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class Classification {

    private List<Section> sections;

    public Classification(List<Section> sections) {
        this.sections = sections;
    }

    public List<Section> getSections() {
        return sections;
    }
}
