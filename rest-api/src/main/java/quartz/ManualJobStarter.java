package quartz;

import org.quartz.SchedulerException;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by bartek on 28.07.17.
 */

public class ManualJobStarter {

    public static final int TRIGGER_MAX_EXECS = 2;

    private static final Logger LOGGER = getLogger(ManualJobStarter.class);

    public static void main(String[] args) {

        CronSchedulerTrigger trigger = new CronSchedulerTrigger(TRIGGER_MAX_EXECS);
        try {
            trigger.fireJob();
        } catch (InterruptedException | SchedulerException ie)
        {
            LOGGER.error(ie.getMessage());
        }
    }

}
