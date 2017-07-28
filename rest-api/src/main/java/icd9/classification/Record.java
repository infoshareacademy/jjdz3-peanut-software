package icd9.classification;

/**
 * @author Mariusz Szymanski
 */
public class Record {

    private String sectionNumber;
    private String section;
    private String subsectionNumber;
    private String subsection;
    private String mainCategoryNumber;
    private String mainCategory;
    private String detailedCategoryNumber;
    private String detailedCategory;

    public String getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsectionNumber() {
        return subsectionNumber;
    }

    public void setSubsectionNumber(String subsectionNumber) {
        this.subsectionNumber = subsectionNumber;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getMainCategoryNumber() {
        return mainCategoryNumber;
    }

    public void setMainCategoryNumber(String mainCategoryNumber) {
        this.mainCategoryNumber = mainCategoryNumber;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getDetailedCategoryNumber() {
        return detailedCategoryNumber;
    }

    public void setDetailedCategoryNumber(String detailedCategoryNumber) {
        this.detailedCategoryNumber = detailedCategoryNumber;
    }

    public String getDetailedCategory() {
        return detailedCategory;
    }

    public void setDetailedCategory(String detailedCategory) {
        this.detailedCategory = detailedCategory;
    }
}
