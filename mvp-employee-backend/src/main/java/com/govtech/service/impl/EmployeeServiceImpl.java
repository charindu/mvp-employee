package com.govtech.service.impl;

import com.govtech.model.Employee;
import com.govtech.repo.EmployeeRepo;
import com.govtech.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee getEmployeeByEmployeeId(String employeeId) {
        return employeeRepo.findByEmployeeId(employeeId);
    }

    @Override
    public List<Employee> getEmployeesByCriteria(double minSalary,
                                                 double maxSalary,
                                                 int offset,
                                                 int limit,
                                                 String order,
                                                 String column) {

        Sort sort = Sort.by(column);
        if (order.equals("+")) {
            sort.ascending();
        } else {
            sort.descending();
        }
        Pageable pageable = PageRequest.of(offset, limit, sort);
        Page<Employee> employeesPage = employeeRepo.findBySalaryBetween( minSalary, maxSalary, pageable);
        return employeesPage.getContent();
    }
}
