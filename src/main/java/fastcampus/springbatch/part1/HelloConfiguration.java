package fastcampus.springbatch.part1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class HelloConfiguration {
	
	public final JobBuilderFactory jobBuilderFactory;
	public final StepBuilderFactory stepBuilderFactory;
	
	public HelloConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}
	
	@Bean
	public Job helloJob() {
		return jobBuilderFactory.get("helloJob")
				.incrementer(new RunIdIncrementer())
				.start(this.helloStep())
				.build();
	}
	
	@Bean
	public Step helloStep() {
		return stepBuilderFactory.get("helloStep")
				.tasklet((contribution, chunckContext)->{
					log.info("hello sprinig batch");
					return RepeatStatus.FINISHED;
				}).build();
	}

}
