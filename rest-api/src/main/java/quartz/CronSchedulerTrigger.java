package quartz;

import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.slf4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Mariusz Szymanski
 */
public class CronSchedulerTrigger implements ILatch {

    private static final Logger LOGGER = getLogger(CronSchedulerTrigger.class);

    private CountDownLatch latch;

    public CronSchedulerTrigger(int count) {
        this.latch = new CountDownLatch(count);
    }

    public void fireJob() throws SchedulerException, InterruptedException {
        SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        JobBuilder jobBuilder = JobBuilder.newJob(ReportSenderJob.class);
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("latch", this);

        JobDetail jobDetail = jobBuilder.usingJobData("report", "report_details")
                .usingJobData(dataMap)
                .withIdentity("jobReportMailSender", "peanut-api")
                .build();

        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int min = rightNow.get(Calendar.MINUTE);

        LOGGER.info("Current time: " + new Date());

        scheduler.scheduleJob(jobDetail, CronExpression.fireEveryMinuteStartFromNow(hour, min));

        latch.await();
        LOGGER.info("All triggers executed. Shutdown scheduler");
        scheduler.shutdown();
    }

    @Override
    public void countDown() {
        latch.countDown();
    }
}
