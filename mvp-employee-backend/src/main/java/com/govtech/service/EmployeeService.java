package com.govtech.service;

import com.govtech.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeByEmployeeId(String employeeId);

    Page<Employee> getEmployeesByCriteria(double minSalary, double maxSalary, int offset, int limit, String order, String column);

    Employee getEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    boolean deleteEmployeeById( Long id);

    List<Employee> getAllEmployees();

}

