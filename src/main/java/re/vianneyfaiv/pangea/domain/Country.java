package re.vianneyfaiv.pangea.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.core.io.ClassPathResource;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer countryId;

	private String name;
	private String isoName2;
	private String capitalCity;

	protected Country() {
	}

	public Country(String name, String isoName2, String capitalCity) {
		this.name = name;
		this.isoName2 = isoName2;
		this.capitalCity = capitalCity;
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
}