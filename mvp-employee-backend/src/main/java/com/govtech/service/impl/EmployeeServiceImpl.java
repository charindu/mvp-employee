package com.govtech.service.impl;

import com.govtech.model.Employee;
import com.govtech.repo.EmployeeRepo;
import com.govtech.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee getEmployeeByEmployeeId(String employeeId) {
        return employeeRepo.findByEmployeeId(employeeId);
    }
}
