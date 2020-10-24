package com.govtech.batch.job;

import com.govtech.model.Employee;
import com.govtech.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {

    @Autowired
    private EmployeeService employeeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeItemProcessor.class);

    @Override
    public Employee process(Employee employee) throws Exception {

        if( employee.getSalary() <0){
            return null;
        }

        String employeeId = employee.getEmployeeId();

        Employee dbEmployee = employeeService.getEmployeeByEmployeeId(employeeId);
        if(dbEmployee != null){

            dbEmployee.setName( employee.getName());
            dbEmployee.setSalary( employee.getSalary());

            Employee transformedEmployee = dbEmployee;
            LOGGER.info("Converting (" + employee + ") into (" + transformedEmployee + ")");

            return transformedEmployee;
        }

        String loginName = employee.getLoginName();
        String name = employee.getName();
        double salary = employee.getSalary();

        Employee transformedEmployee = new Employee( employeeId, loginName, name, salary);
        LOGGER.info("Converting (" + employee + ") into (" + transformedEmployee + ")");

        return transformedEmployee;
    }
}
