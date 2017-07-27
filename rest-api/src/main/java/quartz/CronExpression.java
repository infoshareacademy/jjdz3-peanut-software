package quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

/**
 * @author Mariusz Szymanski
 */
public class CronExpression {

    private static final String GROUP_NAME = "ReportsMailSenderWithCroneScheduler";

    public static Trigger fireEveryMinuteStartFromNow(int startHr, int startMin) {
        if (startMin == 59) startMin = 0;
        String cronExpression = "0 " + (startMin + 1) + "/1 " + startHr + " * * ? *";
        String description = "Fire every minute starting at " + startHr + ":" + startMin;
        return TriggerBuilder.newTrigger()
                .withIdentity("fireEveryMinuteFromNow", GROUP_NAME)
                .withSchedule(cronSchedule(description, cronExpression))
                .build();
    }

    private static CronScheduleBuilder cronSchedule(String description, String cronExpression) {
        System.out.println(description + " -> (" + cronExpression + ")");
        return CronScheduleBuilder.cronSchedule(cronExpression);
    }
}
