package com.govtech.batch.process;

import com.govtech.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {

    private static final Logger log = LoggerFactory.getLogger(EmployeeItemProcessor.class);

    @Override
    public Employee process(Employee employee) throws Exception {
        String employeeId = employee.getEmployeeId();
        String loginName = employee.getLoginName();
        String name = employee.getName();
        double salary = employee.getSalary();

        Employee transformedEmployee = new Employee( employeeId, loginName, name, salary);
        log.info("Converting (" + employee + ") into (" + transformedEmployee + ")");

        return transformedEmployee;
    }
}

