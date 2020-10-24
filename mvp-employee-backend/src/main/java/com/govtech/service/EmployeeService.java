package com.govtech.service;

import com.govtech.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeByEmployeeId(String employeeId);

    List<Employee> getEmployeesByCriteria(double minSalary, double maxSalary, int offset, int limit, String order, String column);

    Employee getEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    boolean deleteEmployeeById( Long id);

}

