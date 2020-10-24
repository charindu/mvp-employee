package com.govtech.batch.job;

import com.govtech.model.Employee;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemReader implements ItemReader<Employee> {

    private FlatFileItemReader<Employee> employeeFlatFileItemReader;

    @BeforeStep
    public void reader() {
        employeeFlatFileItemReader = new FlatFileItemReaderBuilder<Employee>()
                .name("employeeItemReader")
                .resource(new ClassPathResource("employee.csv"))
                .delimited()
                .delimiter(",")
                .names(new String[]{"employeeId", "loginName", "name", "salary"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {{
                    setTargetType(Employee.class);
                }})
                .linesToSkip(1)
                .build();
        employeeFlatFileItemReader.open(new ExecutionContext());

    }

    @Override
    public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (employeeFlatFileItemReader != null) {
            return employeeFlatFileItemReader.read();
        } else {
            return null;
        }
    }
}
