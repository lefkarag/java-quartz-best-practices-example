package com.javacodegeeks.quartz.joblistener;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJob implements Job {

    private final Logger log = LoggerFactory.getLogger(MyJob.class);
    
	public void execute(JobExecutionContext context) throws JobExecutionException {
	    String jobName = context.getJobDetail().getKey().toString();
	    log.info("{} is being executed", jobName);
	}
}
