package com.javacodegeeks.quartz.disallowconcurrentexecution;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzDisallowConcurrentExecutionExample {
	
    void run() throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = JobBuilder.newJob(DisallowConcurrentExecutionJob.class)
                .withIdentity("job", "group")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger", "group")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        // wait long enough to see the job execution
        Thread.sleep(15 * 1000);

        scheduler.shutdown(true);
    }

    public static void main(String[] args) throws Exception {
        QuartzDisallowConcurrentExecutionExample example = new QuartzDisallowConcurrentExecutionExample();
        example.run();
    }

}