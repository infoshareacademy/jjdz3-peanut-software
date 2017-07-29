package icd9.classification;

import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class MainCategory {

    private String code;
    private String title;
    private List<DetailedCategory> detailedCategories;

    public MainCategory(String code, String title, List<DetailedCategory> detailedCategories) {
        this.code = code;
        this.title = title;
        this.detailedCategories = detailedCategories;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public List<DetailedCategory> getDetailedCategories() {
        return detailedCategories;
    }
}
