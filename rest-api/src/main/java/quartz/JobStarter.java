package quartz;

import org.quartz.SchedulerException;

/**
 * Created by bartek on 28.07.17.
 */
public class JobStarter {

    public static void main(String[] args) {

        CronSchedulerTrigger trigger = new CronSchedulerTrigger(0);
        try {
            trigger.fireJob();
       } catch (InterruptedException ie)
        {
 //TODO: exception catch
        } catch (SchedulerException se)
        {
 //TODO: 28.07.17 exception catch
        }


    }

}
