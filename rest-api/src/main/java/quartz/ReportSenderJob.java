package quartz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import domain.Survey;
import domain.UserActivity;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Mariusz Szymanski
 */
public class ReportSenderJob implements Job {

    private static final String PEANUT_API_SERVICE_URL = "http://localhost:8080/peanut-api/reports/";

    private static final Logger LOGGER = getLogger(ReportSenderJob.class);

    private static int count;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDetail jobDetail = jobExecutionContext.getJobDetail();

        LOGGER.info("----------------------------------------------------------------------------");
        LOGGER.info("ReportSenderJob start: " + jobExecutionContext.getFireTime());
        LOGGER.info("ReportSenderJob details: " + jobDetail.getJobDataMap().getString("report"));

        Map specialization = getSpecializations();
        Survey[] surveys = getSurveys();
        List<UserActivity> usersActivity = getUsersActivity();
        HtmlReportBuilder builder = new HtmlReportBuilder();
        String htmlReport = builder.prepareReport(specialization, surveys, usersActivity);
        Mailer mailer = new Mailer();
        mailer.sendMail("bartlomiej.olewinski@ifresearch.org", "Peanut Daily Report", htmlReport);

        LOGGER.info("ReportSenderJob end (in milliseconds): " + jobExecutionContext.getJobRunTime() + ", key: " + jobDetail.getKey());
        LOGGER.info("ReportSenderJob next scheduled time: " + jobExecutionContext.getNextFireTime());
        LOGGER.info("----------------------------------------------------------------------------");

        ILatch latch = (ILatch) jobDetail.getJobDataMap().get("latch");
        latch.countDown();
        count++;
        LOGGER.info("Job count " + count);
    }

    public Map getSpecializations()
    {
        Response response = getRouteData("specialization");
        return response.readEntity(Map.class);
    }

    public Survey[] getSurveys()
    {
        Response response = getRouteData("survey");
        return response.readEntity(Survey[].class);
    }

    public List<UserActivity> getUsersActivity()
    {
        Response response = getRouteData("usersActivity");
        String json = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        List<UserActivity> usersActivities = new ArrayList<>();
        try {
            final UserActivity[] activitiesTable = mapper.readValue(json, UserActivity[].class);
            usersActivities.addAll(Arrays.asList(activitiesTable));
            Collections.reverse(usersActivities);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }

        return usersActivities;
    }

    private Response getRouteData(String route)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(PEANUT_API_SERVICE_URL.concat(route));
        return target.request().get();
    }
}
