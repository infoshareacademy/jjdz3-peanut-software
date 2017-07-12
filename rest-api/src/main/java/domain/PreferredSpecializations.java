package domain;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mariusz Szymanski
 */
@Singleton
public class PreferredSpecializations {

    private Map<String, Integer> specializationsStatistics;

    @PostConstruct
    private void postConstruct() {
        specializationsStatistics.put("dentist", 2);
        specializationsStatistics.put("internist", 4);
    }

    public PreferredSpecializations() {
        this.specializationsStatistics = new HashMap<>();
    }

    public Map<String, Integer> getSpecializationsStatistics() {
        return specializationsStatistics;
    }

    public void setSpecializationsStatistics(Map<String, Integer> specializationsStatistics) {
        this.specializationsStatistics = specializationsStatistics;
    }
}
