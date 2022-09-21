package flooringMastery.servicelayer;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import flooringMastery.dao.FlooringMasteryDao;
import flooringMastery.dto.Order;
import flooringMastery.dto.Product;
import flooringMastery.dto.Tax;

public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer{

	
	FlooringMasteryDao flooringMasteryDao;
	
	public FlooringMasteryServiceLayerImpl(FlooringMasteryDao flooringMasteryDao) {
		this.flooringMasteryDao = flooringMasteryDao;
	}
	
	@Override
	public void addOrder(int orderNumber, String customerName, String stateAbbr,
			String productType, BigDecimal area, String date) throws Exception {
		
		dateFormatValidation(date);
		LocalDate currentDate = LocalDate.now();
		LocalDate formattedDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		
		try {
			formattedDate = LocalDate.parse(date, formatter);
			
		}catch(DateTimeParseException e) {
			throw new InvalidLocalDateException("Sorry, but your date was inserted in the wrong format, please enter the date in the format MM-dd-yyyy");
		}
		
		if(ChronoUnit.DAYS.between(currentDate, formattedDate) < 0) {
			throw new NegativeDateResultException("Sorry, but your date is invalid. Please insert a date in the future");
		}
		
		if(customerName == null || customerName.trim().length() == 0) {
			throw new EmptyNameException("Sorry but you need to enter a name");
		}
		
		if(flooringMasteryDao.getTaxByStateAbbreviation(stateAbbr) == null ||
				stateAbbr.length() == 0) {
			throw new InvalidStateException("Sorry, but you need to enter a valid state name");
		}
		
		if(flooringMasteryDao.getProductByProductType(productType) == null ||
			productType.length() == 0) {
			throw new InvalidProductException("Sorry, but the product type you entered does not exist");
		}
		
		Product product = flooringMasteryDao.getProductByProductType(productType);
		Tax tax = flooringMasteryDao.getTaxByStateAbbreviation(stateAbbr);
		Order order = new Order(product, tax, area);
		order.setOrderNumber(orderNumber);
		order.setCustomerName(customerName);
		
		flooringMasteryDao.addOrder(order, date);
	}
	
	@Override
	public List<Order> getAllOrders() throws Exception{
		return flooringMasteryDao.getAllOrders();
	}
	
	@Override
	public Order getOrderByNumberAndDate(int orderNumber, String date) throws Exception{
		
		dateFormatValidation(date);
		Order order = flooringMasteryDao.getOrderByOrderNumberAndDate(orderNumber, date);
		
		if(order == null) {
			throw new InvalidOrderException("Sorry, but the order was not found");
		}
		
		return order;
	}
	
	@Override
	public List<Order> getOrdersByDate(String date) throws Exception{
		
		dateFormatValidation(date);
		
		try {
		List<Order> orders = flooringMasteryDao.getOrdersByDate(date);
		if(orders == null || orders.isEmpty()) {
			throw new InvalidOrderException("Sorry, but no orders were found for this date");
		}
		return orders;
		}
		catch(FileNotFoundException e) {
			throw new Exception("Sorry, but no orders exist at this date");
		}
	}
	
	
	@Override
	public void editOrder(int orderNumber, String userSelection, String change, String date) throws Exception {
		
		dateFormatValidation(date);
		Order order = flooringMasteryDao.getOrderByOrderNumberAndDate(orderNumber, date);
		
		if(order == null) {
			throw new InvalidOrderException("Sorry, but the order was not found");
		}
		
		switch(userSelection) {
		
		case "1":
			order.setCustomerName(change);
			flooringMasteryDao.updateOrder(order, date);
			return;
		case "2":
			Tax tax = flooringMasteryDao.getTaxByStateAbbreviation(change);
			if(tax == null) {
				throw new InvalidStateException("Sorry, but you need to enter an existing state name");
			}
			order.setTax(tax);
			flooringMasteryDao.updateOrder(order, date);
			return;
		case "3":
			Product product = flooringMasteryDao.getProductByProductType(change);
			if(product == null) {
				throw new InvalidProductException("Sorry, but the product type you entered does not exist");
			}
			order.setProduct(product);
			flooringMasteryDao.updateOrder(order, date);
			return;
		case "4":
			BigDecimal newArea = new BigDecimal(change);
			order.setArea(newArea);
			flooringMasteryDao.updateOrder(order, date);
			return;
		default:
			throw new UnsupportedOperationException("Unexpected result");
		}
		
	}
	
	
	@Override
	public void deleteOrder(int orderNumber, Boolean confirmation, String date) throws Exception {
		
		dateFormatValidation(date);
		Order order = flooringMasteryDao.getOrderByOrderNumberAndDate(orderNumber, date);
		
		if(order == null) {
			throw new InvalidOrderException("Sorry, but the order was not found");
		}
		
		if(!confirmation) {
			return;
		}
		else {
			flooringMasteryDao.deleteOrder(order, date);
			return;
		}
	}
	
	
	@Override
	public void dateFormatValidation(String date) throws InvalidLocalDateException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		
		try {
			LocalDate formattedDate = LocalDate.parse(date, formatter);
			return;
			
		}catch(DateTimeParseException e) {
			throw new InvalidLocalDateException("Sorry, but your date was inserted in the wrong format, please enter the date in the format MM-dd-yyyy");
		}
	}
	
}
