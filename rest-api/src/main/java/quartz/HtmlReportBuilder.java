package quartz;

import domain.Survey;
import domain.UserActivity;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by bartek on 29.07.17.
 */
public class HtmlReportBuilder implements ReportBuilder {

    @Override
    public String getSpecializationsTable(Map preferredSpecializations)
    {
        StringBuilder html = new StringBuilder("<table class='table table-condensed'><tr><th>Specialization</th><th>Number of choices</th></tr>");
        Iterator it = preferredSpecializations.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) it.next();
            html.append("<tr><td>").append(pair.getKey()).append("</td><td>").append(pair.getValue()).append("</td></tr>");
        }
        html.append("</table>");
        return html.toString();
    }

    @Override
    public String getSurveysTable(Survey[] surveys)
    {
        StringBuilder html = new StringBuilder("<table class='table table-condensed'>");
        html.append("<tr>");
        html.append("<th>Name</th>");
        html.append("<th>Surname</th>");
        html.append("<th>Pesel</th>");
        html.append("<th>Sex</th>");
        html.append("<th>Email</th>");
        html.append("<th>Preferred specialization</th>");
        html.append("<th>Preferred day</th>");
        html.append("</tr>");

        for(Survey s : surveys)
        {
            html.append("<tr>");
            html.append("<td>").append(s.getName()).append("</td>");
            html.append("<td>").append(s.getSurname()).append("</td>");
            html.append("<td>").append(s.getPesel()).append("</td>");
            html.append("<td>").append(s.getSex()).append("</td>");
            html.append("<td>").append(s.getEmail()).append("</td>");
            html.append("<td>").append(s.getPreferedSpecialization()).append("</td>");
            html.append("<td>").append(s.getPreferedDay()).append("</td>");
            html.append("</tr>");
        }
        html.append("</table>");
        return html.toString();
    }

    @Override
    public String getUsersActivityTable(List<UserActivity> activities)
    {
        StringBuilder html = new StringBuilder("<table class='table table-condensed'>");
        html.append("<tr>");
        html.append("<th>Email</th>");
        html.append("<th>Login</th>");
        html.append("<th>Logout</th>");
        html.append("</tr>");

        for(UserActivity a : activities)
        {
            html.append("<tr>");
            html.append("<td>").append(a.getUser().getEmail()).append("</td>");
            html.append("<td>").append(a.getLoginTime()).append("</td>");
            html.append("<td>").append(a.getLogoutTime()).append("</td>");
            html.append("</tr>");
        }
        html.append("</table>");
        return html.toString();
    }

    public String prepareReport(Map preferredSpecializations, Survey[] surveys, List<UserActivity> activityList)
    {
        HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
        StringBuilder html = new StringBuilder();

        html.append("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");
        html.append("<div class=\"container\">");
        html.append("<h2>Preferred specializations</h2>");
        String specsTable = htmlReportBuilder.getSpecializationsTable(preferredSpecializations);
        html.append(specsTable);

        html.append("<p></p>");
        html.append("<h2>Surveys</h2>");
        String surveyTable = htmlReportBuilder.getSurveysTable(surveys);
        html.append(surveyTable);

        html.append("<p></p>");
        html.append("<h2>Users activity</h2>");
        String activityTable = htmlReportBuilder.getUsersActivityTable(activityList);
        html.append(activityTable );

        html.append("</div>");
        return html.toString();
    }
}
