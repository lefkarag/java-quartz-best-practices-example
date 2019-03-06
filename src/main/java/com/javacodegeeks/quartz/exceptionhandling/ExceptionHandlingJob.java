package com.javacodegeeks.quartz.exceptionhandling;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandlingJob implements Job {
    
    private final Logger log = LoggerFactory.getLogger(ExceptionHandlingJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getMergedJobDataMap();
        int retries = dataMap.getInt("retries");
        
        try {
            // exception will be thrown - don't do that!
            int result = 10 / 0;

            log.info("ExceptionHandlingJob never reaches this line");
        } catch (ArithmeticException e) {
            log.error("Exception occured during execution of ExceptionHandlingJob, retry {} more time(s)", retries);
            // decrease the number of retries of the job data map
            dataMap.put("retries", --retries);

            JobExecutionException e2 = new JobExecutionException(e);
            // this job will refire immediately
            if (retries > 0) {
                e2.setRefireImmediately(true);
                throw e2;
            }
        }
    }
}
