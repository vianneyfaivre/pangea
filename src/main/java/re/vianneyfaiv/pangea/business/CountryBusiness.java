package re.vianneyfaiv.pangea.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import re.vianneyfaiv.pangea.batch.bean.CountryBatchBean;
import re.vianneyfaiv.pangea.domain.Country;
import re.vianneyfaiv.pangea.service.CountryRepository;

@Configuration
public class CountryBusiness {

	@Autowired
	private CountryRepository countryRepo;

	public Country create(CountryBatchBean item) {

		Country country = new Country(item.getName(), item.getIso2Code());

		this.countryRepo.save(country);

		return country;
	}

	public List<Country> getAllCountries() {
		return this.countryRepo.findAll();
	}
}
