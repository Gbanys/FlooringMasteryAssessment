package flooringMastery.dao;

import java.time.LocalDate;
import java.util.List;

import flooringMastery.dto.Order;
import flooringMastery.dto.Product;
import flooringMastery.dto.Tax;

public interface FlooringMasteryDao {

	void addOrder(Order order, String date) throws Exception;
	void addProductsAndTaxes(Product product, Tax tax) throws Exception;
	void updateOrder(Order order, String date) throws Exception;
	void deleteOrder(Order order, String date) throws Exception;
	Order getOrderByOrderNumberAndDate(int orderNumber, String date) throws Exception;
	List<Order> getOrdersByDate(String date) throws Exception;
	
	List<Order> getAllOrders() throws Exception;
	List<Product> getAllProducts() throws Exception;
	List<Tax> getAllTaxes() throws Exception;
	
	Product getProductByProductType(String productType) throws Exception;
	Tax getTaxByStateAbbreviation(String stateName) throws Exception;
}
