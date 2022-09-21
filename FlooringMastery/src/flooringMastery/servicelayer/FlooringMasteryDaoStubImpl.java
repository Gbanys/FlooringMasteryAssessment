package flooringMastery.servicelayer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import flooringMastery.dao.FlooringMasteryDao;
import flooringMastery.dto.Order;
import flooringMastery.dto.Product;
import flooringMastery.dto.Tax;

public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao{

	public Order order;
	public Product product;
	public Tax tax;
	public String date;
	
	FlooringMasteryDaoStubImpl(){
		product = new Product("Wood", new BigDecimal("3.00"), new BigDecimal("2.50"));
		tax = new Tax("OK", "Oklahoma", new BigDecimal("10.00"));
		order = new Order(product, tax, new BigDecimal("20.00"));
		order.setOrderNumber(1);
		order.setCustomerName("John Doe");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate tempdate = LocalDate.parse("01-01-2000", formatter);
		date = tempdate.format(formatter);
	}
	FlooringMasteryDaoStubImpl(Order order, Product product, Tax tax){
		this.order = order;
		this.tax = tax;
		this.product = product;
	}
	
	public void addOrder(Order order, String date) {
		return;
	}
	
	public void addProductsAndTaxes(Product product, Tax tax) {
		return;
	}
	
	public void updateOrder(Order order, String date) {
		return;
	}
	
	public void deleteOrder(Order order, String date) {
		return;
	}
	public Order getOrderByOrderNumberAndDate(int orderNumber, String inputdate) {
		if(orderNumber == order.getOrderNumber() && date.equals(inputdate)) {
			return order;
		}
		else {
			return null;
		}
	}
	public List<Order> getOrdersByDate(String date){
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order);
		return orderList;
	}
	
	@Override
	public List<Order> getAllOrders(){
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order);
		return orderList;
	}
	
	@Override 
	public List<Product> getAllProducts(){
		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		return productList;
	}
	
	@Override
	public List<Tax> getAllTaxes(){
		List<Tax> taxList = new ArrayList<Tax>();
		taxList.add(tax);
		return taxList;
	}
	
	@Override
	public Product getProductByProductType(String productType) {
		if(productType.equals(product.getProductType())) {
			return product;
		}
		else {
			return null;
		}
	}
	
	@Override
	public Tax getTaxByStateAbbreviation(String stateAbbr) {
		if(stateAbbr.equals(tax.getStateAbbreviation())) {
			return tax;
		}
		else {
			return null;
		}
	}
}
