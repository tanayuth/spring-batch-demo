package com.example.batchdemo.configuration.flow;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Configuration
public class FlowLastConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    @Autowired
    public FlowLastConfiguration(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    public Job flowLastJob(Flow flow, Step myStep) {
        return jobBuilderFactory.get("FlowLastJob_" + Instant.now().getEpochSecond())
                .start(myStep)
                .on("COMPLETED").to(flow)
                .end()
                .build();
    }

}
