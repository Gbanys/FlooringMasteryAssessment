package flooringMastery.view;

import java.math.BigDecimal;
import java.util.List;

import flooringMastery.dto.Order;

public class FlooringMasteryView {
	
	UserIO io;
	
	public FlooringMasteryView(UserIO io) {
		this.io = io;
	}
	

	public String getMainMenuSelection(){
		
		io.print("Please choose what you would like to do by making a selection from 1 to 6");
		io.print("");
		io.print("****************************************");
		io.print("* 1. Display orders");
		io.print("* 2. Add an order");
		io.print("* 3. Edit an order");
		io.print("* 4. Remove an order");
		io.print("* 5. Export all data");
		io.print("* 6. Quit");
		io.print("****************************************");
		io.print("");
		io.print("Please choose what you would like to do by making a selection from 1 to 6");
		
		String userSelection = io.readString("");
		return userSelection;
	}
	
	public String editOrderUserSelection(){
		
		io.print("****************************************");
		io.print("* 1. Customer Name");
		io.print("* 2. State");
		io.print("* 3. Product Type");
		io.print("* 4. Area");
		io.print("****************************************");
		io.print("");
		io.print("Please select the data that you would like to change by making a selection from 1 to 4");
		
		String userSelection = io.readString("");
		return userSelection;
	}
	
	public String getNewCustomerName() {
		String newCustomerName = io.readString("Please enter a new customer name");
		return newCustomerName;
	}
	public String getNewStateAbbreviation() {
		String newStateAbbreviation = io.readString("Please enter new state abbreviation");
		return newStateAbbreviation;
	}
	public String getNewProductType() {
		String newProductType = io.readString("Please enter new product type");
		return newProductType;
	}
	public String getNewArea() {
		String area = io.readString("Please enter a new area");
		return area;
	}
	
	public void displayOrdersByDate(List<Order> orders) {
		
		for(Order order : orders) {
			io.print("Order Number: " + order.getOrderNumber());
			io.print("Customer name: " + order.getCustomerName());
			io.print("State: " + order.getTax().getStateName());
			io.print("TaxRate: " + order.getTax().getTaxRate());
			io.print("Product type: " + order.getProduct().getProductType());
			io.print("Area: " + order.getArea());
			io.print("Cost per square foot: " + order.getProduct().getCostPerSquareFoot());
			io.print("Labor per square foot: " + order.getProduct().getLaborCostPerSquareFoot());
			io.print("Material cost: " + order.getMaterialCost());
			io.print("Labor cost: " + order.getLabourCost());
			io.print("Tax: " + order.getTotalTax());
			io.print("Total: " + order.getTotal());
			io.print("");
		}
	}
	
	public void displayOrder(Order order) {
		
		io.print("Order Number: " + order.getOrderNumber());
		io.print("Customer name: " + order.getCustomerName());
		io.print("State: " + order.getTax().getStateName());
		io.print("TaxRate: " + order.getTax().getTaxRate());
		io.print("Product type: " + order.getProduct().getProductType());
		io.print("Area: " + order.getArea());
		io.print("Cost per square foot: " + order.getProduct().getCostPerSquareFoot());
		io.print("Labor per square foot: " + order.getProduct().getLaborCostPerSquareFoot());
		io.print("Material cost: " + order.getMaterialCost());
		io.print("Labor cost: " + order.getLabourCost());
		io.print("Tax: " + order.getTotalTax());
		io.print("Total: " + order.getTotal());
		io.print("");
	}
	
	public String getUserDate() {
		
		String userInput = io.readString("Please input the date for the order in the format MM-dd-yyyy");
		return userInput;
	}
	
	public int getOrderNumber() {
		
		int orderNumber = io.readInteger("Please input the new product's order number");
		return orderNumber;
	}
	
	public String getCustomerName() {
		
		String customerName = io.readString("Please enter a new customer name");
		return customerName;
	}
	public String getStateAbbreviation() {
		
		String stateAbbreviation = io.readString("Please enter the state abbreviation");
		return stateAbbreviation;
	}
	public String getProductType() {
		
		String productType = io.readString("Please enter an existing product you want to order");
		return productType;
	}
	public String getArea() {
	
		String area = io.readString("Please enter your flooring area");
		return area;
	}
	
	public String getDeleteConfirmation() {
		io.print("Are you sure that you would like to delete this order?");
		io.print("Write 'YES' to delete the order permanently or write 'NO' to go back.");
		String deleteConfirmation = io.readString("");
		return deleteConfirmation;
	}
	
	public void addOrderSuccessMessage() {
		io.print("Order has been added successfully!!");
	}
	public void editOrderSuccessMessage() {
		io.print("Order has been edited successfully");
	}
	public void deleteOrderSuccessMessage() {
		io.print("Order has been successfully deleted!");
	}
	public void displayExitMessage() {
		io.print("Goodbye!");
	}
}
