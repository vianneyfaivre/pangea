package re.vianneyfaiv.pangea.batch.config.steps;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;

import lombok.Getter;
import re.vianneyfaiv.pangea.batch.bean.CountryBatchBean;
import re.vianneyfaiv.pangea.business.CountryBusiness;

@Configuration
public class CreateCountriesStep {

	@Autowired @Getter private CountriesItemReader reader;
	@Autowired @Getter private CountriesItemWriter writer;

	@Configuration
	protected class CountriesItemReader implements ItemReader<CountryBatchBean> {

		private Iterator<CountryBatchBean> items;

		public CountriesItemReader() {
			JsonFactory factory = new JsonFactory();
			ObjectMapper mapper = new ObjectMapper(factory);

			// read datasource
			ClassPathResource source = new ClassPathResource("batch/countries.json");

			try (JsonParser parser = factory.createParser(source.getFile())) {

				// List<CountryBatchBean>
				CollectionLikeType type = mapper.getTypeFactory().constructCollectionLikeType(List.class, CountryBatchBean.class);

				// convert json to POJO list
				List<CountryBatchBean> list = (List<CountryBatchBean>) mapper.readValue(parser, type);

				this.items = list.iterator();
			} catch (IOException e) {
				throw new ItemStreamException(e);
			}
		}

		@Override
		public CountryBatchBean read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException  {
			if(this.items.hasNext()) {
				return this.items.next();
			} else {
				return null;
			}
		}
	}

	@Configuration
	protected class CountriesItemWriter implements ItemWriter<CountryBatchBean> {

		@Autowired
		private CountryBusiness countryBusiness;

		@Override
		public void write(List<? extends CountryBatchBean> countries) throws Exception {
			countries
				.stream()
				.forEach(country -> this.countryBusiness.create(country));
		}
	}

}
