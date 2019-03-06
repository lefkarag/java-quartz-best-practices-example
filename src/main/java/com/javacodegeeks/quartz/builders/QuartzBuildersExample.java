package com.javacodegeeks.quartz.builders;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzBuildersExample {

    public void run() throws Exception {
        // create the scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // define the job and tie it to the SimpleJob class
        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("myJob", "myGroup")
                .build();

        // create the trigger and define its schedule to run every 3 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "myGroup")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();

        // add the job details to the scheduler and associate it with the trigger
        scheduler.scheduleJob(job, trigger);

        // start the scheduler
        scheduler.start();

        // wait long enough to see the job execution
        Thread.sleep(5 * 1000);

        // shutdown the scheduler
        scheduler.shutdown(true);
    }

    public static void main(String[] args) throws Exception {
        // run the example
        QuartzBuildersExample example = new QuartzBuildersExample();
        example.run();
    }

}
