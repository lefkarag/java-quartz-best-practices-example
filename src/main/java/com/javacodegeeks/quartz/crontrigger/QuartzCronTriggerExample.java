package com.javacodegeeks.quartz.crontrigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.javacodegeeks.quartz.builders.SimpleJob;

public class QuartzCronTriggerExample {

    public void run() throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("myJob", "myGroup")
                .build();

        // Create the trigger and define a cron schedule of every 2 seconds. Avoid setting this to DST of your locale
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "myGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        // wait long enough to see the job execution
        Thread.sleep(5 * 1000);

        scheduler.shutdown(true);
    }

    public static void main(String[] args) throws Exception {
        QuartzCronTriggerExample example = new QuartzCronTriggerExample();
        example.run();
    }

}
