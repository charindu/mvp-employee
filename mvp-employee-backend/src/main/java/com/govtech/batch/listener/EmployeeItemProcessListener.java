package com.govtech.batch.listener;

import com.govtech.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemProcessListener implements ItemProcessListener<Employee, Employee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeItemProcessListener.class);

    @Override
    public void beforeProcess(Employee item) {
        LOGGER.info("beforeProcess");
    }

    @Override
    public void afterProcess(Employee item, Employee result) {
        LOGGER.info("afterProcess: " + item + " ---> " + result);
    }

    @Override
    public void onProcessError(Employee item, Exception e) {
        LOGGER.info("onProcessError");
    }
}