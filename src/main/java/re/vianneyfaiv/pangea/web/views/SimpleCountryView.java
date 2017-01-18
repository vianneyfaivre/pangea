package re.vianneyfaiv.pangea.web.views;

import javax.validation.constraints.NotNull;

import re.vianneyfaiv.pangea.domain.Country;

public class SimpleCountryView {

	private String name;
	private String flagPath;

	public SimpleCountryView(@NotNull Country country) {
		this.name = country.getName();
		this.flagPath = country.getFlagPath();
	}

	@Override
	public String toString() {
		return "SimpleCountryView [name=" + this.name + ", flagPath=" + this.flagPath + "]";
	}

	public String getName() {
		return this.name;
	}

	public String getFlagPath() {
		return this.flagPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.flagPath == null) ? 0 : this.flagPath.hashCode());
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		SimpleCountryView other = (SimpleCountryView) obj;
		if (this.flagPath == null) {
			if (other.flagPath != null) {
				return false;
			}
		} else if (!this.flagPath.equals(other.flagPath)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
