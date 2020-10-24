package com.govtech.controller;

import com.govtech.model.Employee;
import com.govtech.service.EmployeeService;
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
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    private EmployeeService employeeService;

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

    @GetMapping(path = "/users")
    public ResponseEntity<Map> getUsersExt(@RequestParam(name = "minSalary") String reqMinSalary,
                                      @RequestParam(name = "maxSalary") String reqMaxSalary,
                                      @RequestParam(name = "offset") String reqOffset,
                                      @RequestParam(name = "limit") String reqLimit,
                                      @RequestParam(name = "sort") String sort){

        Map<String, Object> returnMap = new HashMap<>();
        double minSalary = 0;
        double maxSalary = 0;
        int offset = 0;
        int limit = 0;
        String sortOrder ="";
        String sortColumn ="";
        boolean isValidParams = true;
        try {
            minSalary =  Double.parseDouble(reqMinSalary);
            maxSalary =  Double.parseDouble(reqMaxSalary);
            offset =  Integer.parseInt(reqOffset);
            limit =  Integer.parseInt(reqLimit);
            sortOrder = sort.substring(0,1);
            sortColumn = sort.substring(1);
        }catch ( Exception ex){
            returnMap.put("status", HttpStatus.BAD_REQUEST.value());
            isValidParams = false;
        }
        if(isValidParams){
            List<Employee> employeeList =  employeeService.getEmployeesByCriteria( minSalary, maxSalary, offset, limit, sortOrder, sortColumn);
            returnMap.put("result", employeeList);
            returnMap.put("status", HttpStatus.OK.value());
        }

        return ResponseEntity.ok(returnMap);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map> handleMissingParams(MissingServletRequestParameterException ex) {
        Map<String, Object> returnMap = new HashMap<>();
        String name = ex.getParameterName();
        LOGGER.error(name + " parameter is missing");
        returnMap.put("status", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.ok(returnMap);
    }
}
