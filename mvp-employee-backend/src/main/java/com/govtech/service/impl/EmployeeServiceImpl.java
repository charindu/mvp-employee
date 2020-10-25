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
    public Page<Employee> getEmployeesByCriteria(double minSalary,
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
        Page<Employee> employeesPage = null;
        if(minSalary < 0 && maxSalary < 0){
            employeesPage  = employeeRepo.findAll(pageable);
        }else{
            employeesPage  = employeeRepo.findBySalaryBetween( minSalary, maxSalary, pageable);
        }

        return employeesPage;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).get();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public boolean deleteEmployeeById(Long id) {
        boolean isExist = employeeRepo.existsById(id);
        if(isExist){
            employeeRepo.deleteById( id);
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }
}
