package com.javacodegeeks.quartz.jobdatamap;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataMapJob implements Job {
    
    private final Logger log = LoggerFactory.getLogger(DataMapJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getMergedJobDataMap();
        String jobName = dataMap.getString("jobName");
        int jobVersion = dataMap.getInt("jobVersion");
        String triggerName = dataMap.getString("triggerName");

        log.info("The name and version of job is {} {}, triggered by {}!", jobName, jobVersion, triggerName);
    }
}
