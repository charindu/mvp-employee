package com.govtech.batch.config;

import com.govtech.batch.job.EmployeeItemProcessor;
import com.govtech.batch.job.EmployeeItemReader;
import com.govtech.batch.job.EmployeeItemWriter;
import com.govtech.batch.listener.EmployeeItemProcessListener;
import com.govtech.batch.listener.EmployeeItemReaderListener;
import com.govtech.batch.listener.EmployeeItemWriterListener;
import com.govtech.batch.listener.EmployeeJobExecutionListener;
import com.govtech.model.Employee;
import net.bytebuddy.utility.RandomString;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
public class JobBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public EmployeeItemReader reader() {
        return new EmployeeItemReader();
    }

    @Bean
    public EmployeeItemProcessor processor() {
        return new EmployeeItemProcessor();
    }

    @Bean
    public EmployeeItemWriter writer() {
        return new EmployeeItemWriter();
    }

    @Bean
    public EmployeeJobExecutionListener jobExecutionListener() {
        return new EmployeeJobExecutionListener();
    }

    @Bean
    public EmployeeItemReaderListener readerListener() {
        return new EmployeeItemReaderListener();
    }

    @Bean
    public EmployeeItemProcessListener creditCardItemProcessListener() {
        return new EmployeeItemProcessListener();
    }

    @Bean
    public EmployeeItemWriterListener writerListener() {
        return new EmployeeItemWriterListener();
    }

    @Bean
    public Job job(Step step, EmployeeJobExecutionListener jobExecutionListener) {
        String jobName = getJobName();
        Job job = jobBuilderFactory.get(jobName)
                .listener(jobExecutionListener)
                .flow(step)
                .end()
                .build();
        return job;
    }

    @Bean
    public Step step(EmployeeItemReader reader,
                     EmployeeItemWriter writer,
                     EmployeeItemProcessor processor,
                     EmployeeItemReaderListener readerListener,
                     EmployeeItemProcessListener creditCardItemProcessListener,
                     EmployeeItemWriterListener writerListener) {

        TaskletStep step = stepBuilderFactory.get("step2")
                .<Employee, Employee>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(readerListener)
                .listener(creditCardItemProcessListener)
                .listener(writerListener)
                .build();
        return step;
    }

    public static String getJobName(){
        RandomString generatedString = new RandomString(8);
        return  generatedString.toString();
    }
}

