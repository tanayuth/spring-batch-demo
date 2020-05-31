package com.example.batchdemo.configuration.flow;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FlowConfiguration {


    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    public FlowConfiguration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Flow foo() {

        FlowBuilder<Flow> flowFlowBuilder = new FlowBuilder<>("foo");
        flowFlowBuilder.start(flowStep1())
                .next(flowStep2())
                .end();
        return flowFlowBuilder.build();
    }

    @Bean
    public Step flowStep1() {
        return stepBuilderFactory
                .get("Flow Step 1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>> This is step 1 from inside flow foo <<<");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step flowStep2() {
        return stepBuilderFactory
                .get("Flow Step 2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>> This is step 2 from inside flow foo <<<");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
