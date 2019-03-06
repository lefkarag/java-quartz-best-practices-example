package com.javacodegeeks.quartz.exceptionhandling;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzExceptionHandlingExample {
	
    public void run() throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("retries", 3);

        JobDetail job = JobBuilder.newJob(ExceptionHandlingJob.class)
                .withIdentity("job", "group")
                .usingJobData(jobDataMap)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger", "group")
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        // wait long enough to see the job execution
        Thread.sleep(5 * 1000);

        scheduler.shutdown(true);
    }

    public static void main(String[] args) throws Exception {
        QuartzExceptionHandlingExample example = new QuartzExceptionHandlingExample();
        example.run();
    }

}