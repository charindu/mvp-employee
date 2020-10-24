package com.govtech.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @PostMapping("/users/upload")
    public ResponseEntity<Map> uploadUsers(){
        Map<String, Integer> returnMap = new HashMap<>();
        try {
            jobLauncher.run(job, new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters());
        } catch ( JobExecutionAlreadyRunningException
                  | JobRestartException
                  | JobInstanceAlreadyCompleteException
                  | JobParametersInvalidException e) {
            LOGGER.error( e.getMessage(), e.getStackTrace());
            returnMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.ok(returnMap);
        }
        returnMap.put("status", HttpStatus.OK.value());
        return ResponseEntity.ok(returnMap);
    }
}