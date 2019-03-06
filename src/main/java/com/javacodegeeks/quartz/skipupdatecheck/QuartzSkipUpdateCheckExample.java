package com.javacodegeeks.quartz.skipupdatecheck;

import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.javacodegeeks.quartz.builders.SimpleJob;

public class QuartzSkipUpdateCheckExample {

    public void run() throws Exception {
        Properties props = new Properties();
        props.setProperty("org.quartz.scheduler.skipUpdateCheck", "true");
        props.setProperty("org.quartz.threadPool.threadCount", "3");
    
        SchedulerFactory schedulerFactory = new StdSchedulerFactory(props);
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("myJob", "myGroup")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "myGroup")
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        // wait long enough to see the job execution
        Thread.sleep(5 * 1000);

        scheduler.shutdown(true);
    }

    public static void main(String[] args) throws Exception {
        QuartzSkipUpdateCheckExample example = new QuartzSkipUpdateCheckExample();
        example.run();
    }

}
