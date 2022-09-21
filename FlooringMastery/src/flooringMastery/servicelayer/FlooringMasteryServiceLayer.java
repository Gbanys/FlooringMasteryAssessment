package flooringMastery.servicelayer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import flooringMastery.dto.Order;

public interface FlooringMasteryServiceLayer {

	void addOrder(int orderNumber, String customerName, String stateAbbr,
			String productType, BigDecimal area, String date) throws Exception;
	
	List<Order> getAllOrders() throws Exception;
	
	Order getOrderByNumberAndDate(int orderNumber, String date) throws Exception;
	
	List<Order> getOrdersByDate(String date) throws Exception;
	
	void editOrder(int orderNumber, String userSelection, String change, String date) throws Exception;
	
	void deleteOrder(int orderNumber, Boolean confirmation, String date) throws Exception;
	
	void dateFormatValidation(String date) throws InvalidLocalDateException;
}
