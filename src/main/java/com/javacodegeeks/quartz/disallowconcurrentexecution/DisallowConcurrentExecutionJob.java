package com.javacodegeeks.quartz.disallowconcurrentexecution;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisallowConcurrentExecution
public class DisallowConcurrentExecutionJob implements Job {
    
    private final Logger log = LoggerFactory.getLogger(DisallowConcurrentExecutionJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            log.info("DisallowConcurrentExecutionJob executed on {}", new Date());
            Thread.sleep(5000); // Don't do that! It's for the sake of the exercise..
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
