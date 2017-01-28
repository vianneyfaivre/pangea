package re.vianneyfaiv.pangea.batch.config.steps;

import java.nio.charset.StandardCharsets;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import re.vianneyfaiv.pangea.batch.bean.CountryIso3166BatchBean;
import re.vianneyfaiv.pangea.business.CountryBusiness;

@Configuration
@Qualifier(value = "stepCountryIso3166")
public class PangeaBatchStepImportCountriesIso3166 implements PangeaBatchStep<CountryIso3166BatchBean> {

	@Autowired
	private CountryBusiness countryBusiness;

	@Override
	public ItemWriter<CountryIso3166BatchBean> writer() {
		return items -> {
			for (CountryIso3166BatchBean item : items) {
				if(item.ignore()) {
					continue;
				}
				this.countryBusiness.create(item);
			}
		};
	}

	@Override
	public FlatFileItemReader<CountryIso3166BatchBean> reader() {
		return this.countriesReader2();
	}

	public FlatFileItemReader<CountryIso3166BatchBean> countriesReader2() {
		FlatFileItemReader<CountryIso3166BatchBean> reader = new FlatFileItemReader<CountryIso3166BatchBean>();
		reader.setResource(new ClassPathResource("batch/countries-iso3166.csv"));
		reader.setLinesToSkip(1);
		reader.setEncoding(StandardCharsets.UTF_8.name());
		reader.setLineMapper(new DefaultLineMapper<CountryIso3166BatchBean>() {
			{
				this.setLineTokenizer(new DelimitedLineTokenizer() {
					{
						this.setNames(new String[] { "Name", "Code", "Ignore" });
					}
				});
				this.setFieldSetMapper(new BeanWrapperFieldSetMapper<CountryIso3166BatchBean>() {
					{
						this.setTargetType(CountryIso3166BatchBean.class);
					}
				});
			}
		});
		return reader;
	}
}
