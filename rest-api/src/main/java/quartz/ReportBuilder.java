package quartz;

import domain.Survey;
import domain.UserActivity;
import java.util.List;
import java.util.Map;

/**
 * Created by bartek on 29.07.17.
 */
public interface ReportBuilder {

    public String prepareReport(Map preferredSpecializations, Survey[] surveys, List<UserActivity> activities);
    public String getSpecializationsTable(Map preferredSpecializations);
    public String getSurveysTable(Survey[] surveys);
    public String getUsersActivityTable(List<UserActivity> activities);
}
