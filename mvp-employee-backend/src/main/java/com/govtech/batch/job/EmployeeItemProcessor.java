package com.govtech.batch.job;

import com.govtech.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeItemProcessor.class);

    @Override
    public Employee process(Employee employee) {
        String employeeId = employee.getEmployeeId();
        String loginName = employee.getLoginName();
        String name = employee.getName();
        double salary = employee.getSalary();

        Employee transformedEmployee = new Employee( employeeId, loginName, name, salary);
        LOGGER.info("Converting (" + employee + ") into (" + transformedEmployee + ")");

        return transformedEmployee;
    }
}
