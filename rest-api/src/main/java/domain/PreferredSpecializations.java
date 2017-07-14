package domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mariusz Szymanski
 */
@Singleton
public class PreferredSpecializations {

    private static final String reportPathName = "src/main/resources/reports/preferred_specialization_report.json";
    private static final File file = new File(reportPathName);

    @PersistenceContext
    private EntityManager em;

    public Map<String, Integer> getSpecializationsStatistics(){
        Map<String, Integer> preferredSpecializations = new HashMap<>();
        List<Object[]> result = em
                .createQuery
                        ("SELECT s.preferedSpecialization, count(s.preferedSpecialization) as number "
                                + "FROM Survey s group BY s.preferedSpecialization", Object[].class)
                .getResultList();
        for (Object[] object : result) {
            preferredSpecializations.put((String) object[0], ((Long) object[1]).intValue());
        }
        return preferredSpecializations;
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
