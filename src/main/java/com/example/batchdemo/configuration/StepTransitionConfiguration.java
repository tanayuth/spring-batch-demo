package com.example.batchdemo.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Configuration
public class StepTransitionConfiguration {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    public StepTransitionConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step transitionStep1() {
        return stepBuilderFactory
                .get("Step 1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> This is step 1");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step transitionStep2() {
        return stepBuilderFactory
                .get("Step 2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> This is step 2");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step transitionStep3() {
        return stepBuilderFactory
                .get("Step 3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> This is step 3");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job transitionJobSimpleNext() {
        return jobBuilderFactory.get("transistionJobNext_" + Instant.now().toEpochMilli())
                .incrementer(new RunIdIncrementer())
                .start(transitionStep1())
                .next(transitionStep2())
                .next(transitionStep3())
                .build();
    }
}
