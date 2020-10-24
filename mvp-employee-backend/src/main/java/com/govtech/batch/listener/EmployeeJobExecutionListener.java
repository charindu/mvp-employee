package com.govtech.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeJobExecutionListener implements JobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeJobExecutionListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("afterJob: " + jobExecution.getStatus());
    }
}