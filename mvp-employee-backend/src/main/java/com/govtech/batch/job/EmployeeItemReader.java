package com.govtech.batch.job;

import com.govtech.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemReader implements ItemReader<Employee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeItemReader.class);

    private FlatFileItemReader<Employee> employeeFlatFileItemReader;

    @Value("${datafile.dir.location}")
    private String dataFileDir;

    @BeforeStep
    public void reader() {

        employeeFlatFileItemReader = new FlatFileItemReaderBuilder<Employee>()
                .name("employeeItemReader")
                .resource(getResource())
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
    public Employee read() throws Exception {
        if (employeeFlatFileItemReader != null) {
            return employeeFlatFileItemReader.read();
        } else {
            return null;
        }
    }

    public FileSystemResource getResource(){
        FileSystemResource fileSystemResource = new FileSystemResource(dataFileDir +"employee.csv");
        LOGGER.info( "FileSystemResource path :" + fileSystemResource.getPath() );
        if(!fileSystemResource.exists()){
            fileSystemResource = new FileSystemResource(dataFileDir +"employee.txt");
        }
        return fileSystemResource;
    }

}
