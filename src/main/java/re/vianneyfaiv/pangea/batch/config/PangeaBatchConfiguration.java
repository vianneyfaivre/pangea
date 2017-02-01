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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import re.vianneyfaiv.pangea.batch.bean.CountryBatchBean;
import re.vianneyfaiv.pangea.batch.config.steps.CreateCountriesStep;

@Configuration
@EnableBatchProcessing
public class PangeaBatchConfiguration {

	private static final Logger log = LoggerFactory.getLogger(PangeaBatchConfiguration.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private CreateCountriesStep createCountriesStep;

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
				.flow(this.stepCountries())
				.end().build();
	}

	public Step stepCountries() {
		return this.stepBuilderFactory.get("stepCountries")
				.<CountryBatchBean, CountryBatchBean>chunk(10)
				.reader(this.createCountriesStep.getReader())
				.writer(this.createCountriesStep.getWriter())
				.build();
	}
}