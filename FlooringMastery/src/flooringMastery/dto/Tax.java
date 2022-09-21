package flooringMastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tax {

	private String stateAbbreviation;
	private String stateName;
	private BigDecimal taxRate;
	
	public Tax() {
		
	}
	
	public Tax(String stateAbbreviation, String stateName, BigDecimal taxRate) {
		this.stateAbbreviation = stateAbbreviation;
		this.stateName = stateName;
		this.taxRate = taxRate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(stateAbbreviation, stateName, taxRate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tax other = (Tax) obj;
		return Objects.equals(stateAbbreviation, other.stateAbbreviation) && Objects.equals(stateName, other.stateName)
				&& Objects.equals(taxRate, other.taxRate);
	}

	@Override
	public String toString() {
		return "Tax [stateAbbreviation=" + stateAbbreviation + ", stateName=" + stateName + ", taxRate=" + taxRate
				+ "]";
	}
}
