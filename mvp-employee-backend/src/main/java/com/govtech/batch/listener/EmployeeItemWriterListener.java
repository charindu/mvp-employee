package com.govtech.batch.listener;

import com.govtech.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeItemWriterListener implements ItemWriteListener<Employee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeItemWriterListener.class);

    @Override
    public void beforeWrite(List<? extends Employee> items) {
        LOGGER.info("beforeWrite");
    }

    @Override
    public void afterWrite(List<? extends Employee> items) {
        for (Employee employee : items) {
            LOGGER.info("afterWrite :" + employee.toString());
        }
    }

    @Override
    public void onWriteError(Exception exception, List<? extends Employee> items) {
        LOGGER.info("onWriteError");
    }
}