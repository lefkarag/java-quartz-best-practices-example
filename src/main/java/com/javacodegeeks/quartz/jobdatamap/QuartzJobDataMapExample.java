package com.javacodegeeks.quartz.jobdatamap;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzJobDataMapExample {

    public void run() throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // create a job data map and pass the job name and version
        JobDataMap jobDetailDataMap = new JobDataMap();
        jobDetailDataMap.put("jobName", "MyCustomJob");
        jobDetailDataMap.put("jobVersion", 1);

        // pass the jobDetailDataMap when creating the job detail
        JobDetail job = JobBuilder.newJob(DataMapJob.class)
                .withIdentity("MyCustomJob", "group")
                .usingJobData(jobDetailDataMap)
                .build();

        // create a job data map and pass the trigger name
        JobDataMap triggerJobDataMap = new JobDataMap();
        triggerJobDataMap.put("triggerName", "MyCustomTrigger");

        // pass the triggerJobDataMap when creating the trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("MyCustomTrigger", "group")
                .usingJobData(triggerJobDataMap)
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        // wait long enough to see the job execution
        Thread.sleep(1 * 1000);

        scheduler.shutdown(true);
    }

    public static void main(String[] args) throws Exception {
        QuartzJobDataMapExample example = new QuartzJobDataMapExample();
        example.run();
    }

}