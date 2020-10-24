package com.govtech.batch.listener;

import com.govtech.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemReaderListener implements ItemReadListener<Employee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeItemReaderListener.class);

    @Override
    public void beforeRead() {
        LOGGER.info("beforeRead");
    }

    @Override
    public void afterRead(Employee item) {
        LOGGER.info("afterRead: " + item.toString());
    }

    @Override
    public void onReadError(Exception ex) {
        LOGGER.info("onReadError");
    }
}