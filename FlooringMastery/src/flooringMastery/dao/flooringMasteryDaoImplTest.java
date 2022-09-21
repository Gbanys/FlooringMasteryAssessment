package flooringMastery.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flooringMastery.dto.Order;
import flooringMastery.dto.Product;
import flooringMastery.dto.Tax;

class flooringMasteryDaoImplTest {
	
	FlooringMasteryDaoImpl flooringMasteryDao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		String orderFolderPath = "C:/Users/giedr/eclipse-workspace/FlooringMastery/TestOrders";
		String dataFolderPath = "C:/Users/giedr/eclipse-workspace/FlooringMastery/TestData";
		String productsFile = "C:/Users/giedr/eclipse-workspace/FlooringMastery/TestData/testProducts.txt";
		String taxesFile = "C:/Users/giedr/eclipse-workspace/FlooringMastery/TestData/testTaxes.txt";
		LocalDate timestamp = LocalDate.now();
		File file = new File("C:/Users/giedr/eclipse-workspace/FlooringMastery/TestOrders/Orders_"+timestamp.toString()+".txt");
		file.delete();
		
		File orderFolder = new File(orderFolderPath);
		orderFolder.mkdir();
		File dataFolder = new File(dataFolderPath);
		dataFolder.mkdir();
		new FileWriter(productsFile);
		new FileWriter(taxesFile);
		flooringMasteryDao = new FlooringMasteryDaoImpl(orderFolderPath, productsFile, taxesFile);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAddOrder() throws Exception {
		
		Product product = new Product("testproduct",new BigDecimal("1.00"), new BigDecimal("2.00"));
		Tax tax = new Tax("NY", "New York", new BigDecimal("4.00"));
		Order order = new Order(product, tax, new BigDecimal("5.00"));
		order.setOrderNumber(1);
		order.setCustomerName("test");
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String formattedDate = date.format(formatter);
		
		flooringMasteryDao.addProductsAndTaxes(product, tax);
		flooringMasteryDao.addOrder(order, formattedDate);
		
		Collection<Order> ordersFromDao = flooringMasteryDao.orders.values();
		Iterator<Order> it = ordersFromDao.iterator();
		Order orderFromDao = it.next();
		
		assertEquals(order, orderFromDao);
	}
	
	@Test
	void testGetOrderByOrderNumberAndDate() throws Exception {
		
		Product product = new Product("testproduct",new BigDecimal("1.00"), new BigDecimal("2.00"));
		Tax tax = new Tax("NY", "New York", new BigDecimal("4.00"));
		Order order = new Order(product, tax, new BigDecimal("5.00"));
		order.setOrderNumber(1);
		order.setCustomerName("test");
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String formattedDate = date.format(formatter);
		
		flooringMasteryDao.addProductsAndTaxes(product, tax);
		flooringMasteryDao.addOrder(order, formattedDate);
		
		Order retrievedOrder = flooringMasteryDao.getOrderByOrderNumberAndDate(1, formattedDate);
		assertEquals(order, retrievedOrder);
	}
	
	@Test
	void testUpdateOrder() throws Exception {
		
		Product product = new Product("testproduct",new BigDecimal("1.00"), new BigDecimal("2.00"));
		Tax tax = new Tax("NY", "New York", new BigDecimal("4.00"));
		Order order = new Order(product, tax, new BigDecimal("5.00"));
		order.setOrderNumber(1);
		order.setCustomerName("test");
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String formattedDate = date.format(formatter);
		
		flooringMasteryDao.addProductsAndTaxes(product, tax);
		flooringMasteryDao.addOrder(order, formattedDate);
		
		Order firstOrderFromDao = flooringMasteryDao.getOrderByOrderNumberAndDate(1, formattedDate);
		
		Product secondProduct = new Product("updatedtestproduct",new BigDecimal("4.00"), new BigDecimal("2.50"));
		Tax secondTax = new Tax("OK", "Oklahoma", new BigDecimal("7.00"));
		Order secondOrder = new Order(secondProduct, secondTax, new BigDecimal("3.00"));
		secondOrder.setOrderNumber(1);
		secondOrder.setCustomerName("updated test");
		LocalDate secondDate = LocalDate.now();
		String secondFormattedDate = date.format(formatter);
		
		flooringMasteryDao.addProductsAndTaxes(secondProduct, secondTax);
		flooringMasteryDao.updateOrder(secondOrder, secondFormattedDate);
		
		Order secondOrderFromDao = flooringMasteryDao.getOrderByOrderNumberAndDate(1, secondFormattedDate);
		
		assertNotEquals(firstOrderFromDao, secondOrderFromDao);
		assertEquals(order, firstOrderFromDao);
		assertEquals(secondOrder, secondOrderFromDao);
		assertNotEquals(firstOrderFromDao, secondOrderFromDao);
		assertNotNull(firstOrderFromDao);
		assertNotNull(secondOrderFromDao);
		
	}
	
	@Test
	void testDeleteOrder() throws Exception{
		
		Product product = new Product("testproduct",new BigDecimal("8.00"), new BigDecimal("3.00"));
		Tax tax = new Tax("NY", "New York", new BigDecimal("3.50"));
		Order order = new Order(product, tax, new BigDecimal("2.20"));
		order.setOrderNumber(1);
		order.setCustomerName("test");
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String formattedDate = date.format(formatter);
		
		flooringMasteryDao.addProductsAndTaxes(product, tax);
		flooringMasteryDao.addOrder(order, formattedDate);
		flooringMasteryDao.deleteOrder(order, formattedDate);
		
		Collection<Order> ordersFromDao = flooringMasteryDao.orders.values();
		
		assertTrue(ordersFromDao.isEmpty());
	}
	

}
