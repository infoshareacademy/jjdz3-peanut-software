package quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Mariusz Szymanski
 */
public class ReportSenderJob implements Job {

    private static int count;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDetail jobDetail = jobExecutionContext.getJobDetail();

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("ReportSenderJob start: " + jobExecutionContext.getFireTime());
        System.out.println("ReportSenderJob details: " + jobDetail.getJobDataMap().getString("report"));
//      email.send(report);
        System.out.println("ReportSenderJob end (in milliseconds): " + jobExecutionContext.getJobRunTime() + ", key: " + jobDetail.getKey());
        System.out.println("ReportSenderJob next scheduled time: " + jobExecutionContext.getNextFireTime());
        System.out.println("----------------------------------------------------------------------------");

        ILatch latch = (ILatch) jobDetail.getJobDataMap().get("latch");
        latch.countDown();
        count++;
        System.out.println("Job count " + count);
    }
}
