package flooringMastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

	private int orderNumber;
	private String customerName;
	private BigDecimal area;
	
	private Product product;
	private Tax tax;
	
	private BigDecimal materialCost;
	private BigDecimal labourCost;
	private BigDecimal totalTax;
	private BigDecimal total;
	
	public Order(Product product, Tax tax, BigDecimal area) {
		this.product = product;
		this.tax = tax;
		this.area = area;
		materialCost = (area.multiply(product.getCostPerSquareFoot()));
		labourCost = (area.multiply(product.getLaborCostPerSquareFoot()));
		totalTax = (materialCost.add(labourCost)).multiply(tax.getTaxRate().divide(BigDecimal.valueOf(100)));
		total = materialCost.add(labourCost).add(totalTax);
	}

	@Override
	public int hashCode() {
		return Objects.hash(area, customerName, labourCost, materialCost, orderNumber, product, tax, total, totalTax);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(area, other.area) && Objects.equals(customerName, other.customerName)
				&& Objects.equals(labourCost, other.labourCost) && Objects.equals(materialCost, other.materialCost)
				&& orderNumber == other.orderNumber && Objects.equals(product, other.product)
				&& Objects.equals(tax, other.tax) && Objects.equals(total, other.total)
				&& Objects.equals(totalTax, other.totalTax);
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", customerName=" + customerName + ", area=" + area + ", product="
				+ product + ", tax=" + tax + ", materialCost=" + materialCost + ", labourCost=" + labourCost
				+ ", totalTax=" + totalTax + ", total=" + total + "]";
	}
}
