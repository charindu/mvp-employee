package com.govtech.batch.job;

import com.govtech.model.Employee;
import com.govtech.repo.EmployeeRepo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeItemWriter implements ItemWriter<Employee> {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public void write(List<? extends Employee> items) throws Exception {
        employeeRepo.saveAll(items);
    }
}
