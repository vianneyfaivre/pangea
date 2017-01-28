package re.vianneyfaiv.pangea.web;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import re.vianneyfaiv.pangea.business.CountryBusiness;
import re.vianneyfaiv.pangea.domain.Country;
import re.vianneyfaiv.pangea.web.views.SimpleCountryView;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

	@Autowired
	private CountryBusiness countryBusiness;

	@RequestMapping("/countries")
	public List<SimpleCountryView> getCountriesByFlag(
			@RequestParam Optional<Boolean> hasFlag)
	{
		Predicate<Country> filter = c -> c == c;

		if(hasFlag.isPresent()) {
			filter  = c -> c.hasFlag() == hasFlag.get();
		}

		return this.countryBusiness.getAllCountries()
					.stream()
					.filter(filter)
					.map(c -> new SimpleCountryView(c))
					.collect(Collectors.toList());
	}
}
