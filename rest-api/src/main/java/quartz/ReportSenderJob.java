package quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Mariusz Szymanski
 */
public class ReportSenderJob implements Job {

    private static final Logger LOGGER = getLogger(ReportSenderJob.class);

    private static int count;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDetail jobDetail = jobExecutionContext.getJobDetail();

        LOGGER.info("----------------------------------------------------------------------------");
        LOGGER.info("ReportSenderJob start: " + jobExecutionContext.getFireTime());
        LOGGER.info("ReportSenderJob details: " + jobDetail.getJobDataMap().getString("report"));
//      email.send(report);
        LOGGER.info("ReportSenderJob end (in milliseconds): " + jobExecutionContext.getJobRunTime() + ", key: " + jobDetail.getKey());
        LOGGER.info("ReportSenderJob next scheduled time: " + jobExecutionContext.getNextFireTime());
        LOGGER.info("----------------------------------------------------------------------------");

        ILatch latch = (ILatch) jobDetail.getJobDataMap().get("latch");
        latch.countDown();
        count++;
        LOGGER.info("Job count " + count);
    }
}
