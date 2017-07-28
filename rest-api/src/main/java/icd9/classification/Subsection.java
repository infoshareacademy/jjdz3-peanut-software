package icd9.classification;

import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class Subsection {

    private String code;
    private String title;
    private List<MainCategory> mainCategories;

    public Subsection(String code, String title, List<MainCategory> mainCategories) {
        this.code = code;
        this.title = title;
        this.mainCategories = mainCategories;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public List<MainCategory> getMainCategories() {
        return mainCategories;
    }
}
