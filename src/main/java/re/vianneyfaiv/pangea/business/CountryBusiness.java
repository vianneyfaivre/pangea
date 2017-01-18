package re.vianneyfaiv.pangea.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import re.vianneyfaiv.pangea.batch.bean.CountryIso3166BatchBean;
import re.vianneyfaiv.pangea.domain.Country;
import re.vianneyfaiv.pangea.service.CountryRepository;

@Configuration
public class CountryBusiness {

	@Autowired
	private CountryRepository countryRepo;

	public Country create(CountryIso3166BatchBean item) {

		Country country = new Country(item.getName(), item.getCode());

		this.countryRepo.save(country);

		return country;
	}
}
