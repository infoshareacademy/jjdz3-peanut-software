package domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mariusz Szymanski
 */
@Singleton
public class PreferredSpecializations {

    private Map<String, Integer> specializationsStatistics;
    private File file = new File("src/main/resources/reports/preferred_specialization_report.json");

    @PostConstruct
    private void postConstruct() {
        specializationsStatistics = this.readReportFromFile();
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

    public void writeReportToFile(Map<String, Integer> report) {
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(file, report);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Integer> readReportFromFile() {
        Map<String, Integer> report = new HashMap<>();
        try {
            report = new ObjectMapper().readValue(file, new TypeReference<Map<String, Integer>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;
    }
}
