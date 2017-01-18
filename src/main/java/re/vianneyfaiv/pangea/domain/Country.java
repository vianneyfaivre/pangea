package re.vianneyfaiv.pangea.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.core.io.ClassPathResource;

@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer countryId;

	private String name;
	private String isoName2;

	protected Country() {
	}

	public Country(String name, String isoName2) {
		this.name = name;
		this.isoName2 = isoName2;
	}

	public String getFlagPath() {
		final String flagsPath = "/images/flags-normal/%s.png";

		String countryIso2 = this.isoName2.toLowerCase();

		return String.format(flagsPath, countryIso2);
	}

	public boolean hasFlag() {
		final String flagsPath = "/static/images/flags-normal/%s.png";

		String countryIso2 = this.isoName2.toLowerCase();

		return new ClassPathResource(String.format(flagsPath, countryIso2)).exists();
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsoName2() {
		return this.isoName2;
	}

	public void setIsoName2(String isoName2) {
		this.isoName2 = isoName2;
	}
}