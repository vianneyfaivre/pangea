package re.vianneyfaiv.pangea.batch.config;


import java.io.IOException;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import re.vianneyfaiv.pangea.batch.bean.CountryIso3166BatchBean;
import re.vianneyfaiv.pangea.batch.config.steps.PangeaBatchStep;

@Configuration
@EnableBatchProcessing
public class PangeaBatchConfiguration {

	private static final Logger log = LoggerFactory.getLogger(PangeaBatchConfiguration.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier(value = "stepCountryIso3166")
	public PangeaBatchStep<CountryIso3166BatchBean> stepCountryIso3166;


	@Bean
	public JobExecutionListenerSupport listener() {
		return new JobExecutionListenerSupport() {
			@Override
			public void beforeJob(JobExecution jobExecution) {
				log.info("starting job for Pangea");
			}

			@Override
			public void afterJob(JobExecution jobExecution) {

				long seconds = ChronoUnit.SECONDS.between(jobExecution.getStartTime().toInstant(),
						jobExecution.getEndTime().toInstant());

				log.info("Job ended for Pangea (elapsed : " + seconds + "s)");
			}
		};
	}


	@Bean
	public Job pangeaInitialBatch() throws IOException {
		return this.jobBuilderFactory.get("pangeaInitialBatch")
				.incrementer(new RunIdIncrementer()).listener(this.listener()) //
				.flow(this.stepCountryIso3166()) //
				.end().build();
	}

	public Step stepCountryIso3166() {
		return this.stepBuilderFactory.get("stepCountryIso3166")
				.<CountryIso3166BatchBean, CountryIso3166BatchBean>chunk(10).reader(this.stepCountryIso3166.reader()) //
				.processor(this.stepCountryIso3166.processor()) //
				.writer(this.stepCountryIso3166.writer()).build();
	}

}
