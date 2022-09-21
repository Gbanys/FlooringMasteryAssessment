package flooringMastery.servicelayer;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flooringMastery.dao.FlooringMasteryDao;
import flooringMastery.dto.Order;
import flooringMastery.dto.Product;
import flooringMastery.dto.Tax;

class FlooringMasteryServiceLayerTestImpl {

	private FlooringMasteryServiceLayer service;
	
	public FlooringMasteryServiceLayerTestImpl() {
		FlooringMasteryDao dao = new FlooringMasteryDaoStubImpl();
		service = new FlooringMasteryServiceLayerImpl(dao);
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddValidOrder() {
		
		int orderNumber = 1;
		String customerName = "John Doe";
		String stateAbbr = "OK";
		String productType = "Wood";
		BigDecimal area = new BigDecimal("3.00");
		String date = "10-21-2024";
		
		try {
			service.addOrder(orderNumber, customerName, stateAbbr, productType, area, date);
			return;
		}
		catch(Exception e) {
			e.printStackTrace();
			fail("Order was not created");
		}
	}
	
	@Test
	void testInvalidLocalDate() throws Exception {
		
		int orderNumber = 1;
		String customerName = "John Doe";
		String stateAbbr = "OK";
		String productType = "Wood";
		BigDecimal area = new BigDecimal("3.00");
		String date = "10/21/2024";
		
		try {
			service.addOrder(orderNumber, customerName, stateAbbr, productType, area, date);
			fail("No exception was thrown");
		}
		catch(InvalidLocalDateException e) {
			return;
		}
		catch(Exception e) {
			fail("Incorrect exception was thrown");
		}
	}
	
	@Test
	void testPastLocalDate() throws Exception {
		
		int orderNumber = 1;
		String customerName = "John Doe";
		String stateAbbr = "OK";
		String productType = "Wood";
		BigDecimal area = new BigDecimal("3.00");
		String date = "10-21-2018";
		
		try {
			service.addOrder(orderNumber, customerName, stateAbbr, productType, area, date);
			fail("No exception was thrown");
		}
		catch(NegativeDateResultException e) {
			return;
		}
		catch(Exception e) {
			fail("Incorrect exception was thrown");
		}
	}
	
	@Test
	void testEmptyName() throws Exception {
		

		int orderNumber = 1;
		String customerName = "    ";
		String stateAbbr = "OK";
		String productType = "Wood";
		BigDecimal area = new BigDecimal("3.00");
		String date = "10-21-2024";
		
		
		try {
			service.addOrder(orderNumber, customerName, stateAbbr, productType, area, date);
			fail("No exception was thrown");
		}
		catch(EmptyNameException e) {
			return;
		}
		catch(Exception e) {
			e.printStackTrace();
			fail("Incorrect exception was thrown");
		}
	}
	
	@Test
	void testInvalidState() throws Exception {
		
		int orderNumber = 1;
		String customerName = "John Doe";
		String stateAbbr = "  ";
		String productType = "Wood";
		BigDecimal area = new BigDecimal("3.00");
		String date = "10-21-2024";
		
		try {
			service.addOrder(orderNumber, customerName, stateAbbr, productType, area, date);
			fail("No exception was thrown");
		}
		catch(InvalidStateException e) {
			return;
		}
		catch(Exception e) {
			fail("Incorrect exception was thrown");
		}
	}
	
	@Test
	void testInvalidProduct() throws Exception {
		
		int orderNumber = 1;
		String customerName = "John Doe";
		String stateAbbr = "OK";
		String productType = "   ";
		BigDecimal area = new BigDecimal("3.00");
		String date = "10-21-2024";
		
		try {
			service.addOrder(orderNumber, customerName, stateAbbr, productType, area, date);
			fail("No exception was thrown");
		}
		catch(InvalidProductException e) {
			return;
		}
		catch(Exception e) {
			fail("Incorrect exception was thrown");
		}
	}
	
	@Test
	void testGetOrderByOrderNumberAndDate() {
		
		int orderNumber = 1;
		String customerName = "John Doe";
		String stateAbbr = "OK";
		String productType = "Wood";
		BigDecimal area = new BigDecimal("3.00");
		String date = "10-21-2024";
		
		try {
			service.getOrderByNumberAndDate(orderNumber, "01-01-2000");
			return;

		}catch(Exception e) {
			fail("The order was valid");
		}
	}
	
	@Test
	void testInvalidOrder() throws Exception {
		
		int orderNumber = 1;
		String customerName = "John Doe";
		String stateAbbr = "OK";
		String productType = "Wood";
		BigDecimal area = new BigDecimal("3.00");
		String date = "10-21-2024";
		
		try {
			service.getOrderByNumberAndDate(2, "01-01-2000");
			fail("The order does not exist");

		}catch(InvalidOrderException e) {
			return;
		}
		catch(Exception e) {
			fail("Incorrect exception was thrown");
		}
	}
	
}
