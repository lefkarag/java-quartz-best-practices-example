package com.javacodegeeks.quartz.joblistener;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzJobListenerExample {

    public void run() throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob", "group")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger", "group")
                .build();
        
        // add the MyJobListener to the scheduler
        scheduler.getListenerManager()
                .addJobListener(new MyJobListener());

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        // wait long enough to see the job execution
        Thread.sleep(3 * 1000);

        scheduler.shutdown(true);
    }

    public static void main(String[] args) throws Exception {
        QuartzJobListenerExample example = new QuartzJobListenerExample();
        example.run();
    }

}
