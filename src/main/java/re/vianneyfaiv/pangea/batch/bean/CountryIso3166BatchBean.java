package re.vianneyfaiv.pangea.batch.bean;

public class CountryIso3166BatchBean {

	private String name;
	private String code;

	public CountryIso3166BatchBean() {
	}

	public CountryIso3166BatchBean(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "CountryIso3166BatchBean [name=" + this.name + ", code=" + this.code + "]";
	}

}
