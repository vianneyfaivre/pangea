package re.vianneyfaiv.pangea.batch.bean;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CountryBatchBean {

    private String id;
    private String iso2Code;
    private String name;

    private Region region;
    @Getter @Setter
    public class Region {
    	private String id;
    	private String value;
    }

    private Adminregion adminregion;
    @Getter @Setter
    public class Adminregion {
    	private String id;
    	private String value;
    }

    private IncomeLevel incomeLevel;
    @Getter @Setter
    public class IncomeLevel {
    	private String id;
    	private String value;
    }

    private LendingType lendingType;
    @Getter @Setter
    public class LendingType {
    	private String id;
    	private String value;
    }

    private String capitalCity;
    private String longitude;
    private String latitude;

	@Override
	public String toString() {
		return "CountryBatchBean [id=" + this.id + ", iso2Code=" + this.iso2Code + ", name=" + this.name + ", region=" + this.region
				+ ", adminregion=" + this.adminregion + ", incomeLevel=" + this.incomeLevel + ", lendingType=" + this.lendingType
				+ ", capitalCity=" + this.capitalCity + ", longitude=" + this.longitude + ", latitude=" + this.latitude + "]";
	}
}
