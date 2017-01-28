package re.vianneyfaiv.pangea.batch.bean;

public class CountryIso3166BatchBean {

	private String name;
	private String code;
	private boolean ignore;

	public CountryIso3166BatchBean() {
	}

	public CountryIso3166BatchBean(String name, String code, boolean ignore) {
		this.name = name;
		this.code = code;
		this.ignore = ignore;
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

	public boolean ignore() {
		return this.ignore;
	}

	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}

	@Override
	public String toString() {
		return "CountryIso3166BatchBean [name=" + this.name + ", code=" + this.code + ", ignore=" + this.ignore + "]";
	}
}
