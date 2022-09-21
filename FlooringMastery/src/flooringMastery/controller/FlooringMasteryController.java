package flooringMastery.controller;

import java.math.BigDecimal;
import java.util.List;

import flooringMastery.dto.Order;
import flooringMastery.dto.Product;
import flooringMastery.servicelayer.FlooringMasteryServiceLayer;
import flooringMastery.view.FlooringMasteryView;

public class FlooringMasteryController {

	FlooringMasteryView view;
	FlooringMasteryServiceLayer serviceLayer;
	
	public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer serviceLayer) {
		this.view = view;
		this.serviceLayer = serviceLayer;
	}
	
	public void run() throws Exception {
		
		boolean end = false;
		
		while(end == false) {
			String userSelection = view.getMainMenuSelection();

			switch(userSelection) {
		
			case "1":
				
				String date = view.getUserDate();
				List<Order> orders = serviceLayer.getOrdersByDate(date);
				view.displayOrdersByDate(orders);
				break;
				
			case "2":
				
				int orderNumber = view.getOrderNumber();
				String customerName = view.getCustomerName();
				String stateAbbreviation = view.getStateAbbreviation();
				String productType = view.getProductType();
				String inputArea = view.getArea();
				BigDecimal area = new BigDecimal(inputArea);
				String userDate = view.getUserDate();
				serviceLayer.addOrder(orderNumber, customerName, stateAbbreviation, productType, area, userDate);
				view.addOrderSuccessMessage();
				break;
				
			case "3":
				
				int orderEditNumber = view.getOrderNumber();
				String dateForEdit = view.getUserDate();
				String userChoice = view.editOrderUserSelection();
				String userChange;
				
				switch(userChoice) {
				
				case "1":
					userChange = view.getCustomerName();
					serviceLayer.editOrder(orderEditNumber, userChoice, userChange, dateForEdit);
					break;
				case "2":
					userChange = view.getStateAbbreviation();
					serviceLayer.editOrder(orderEditNumber, userChoice, userChange, dateForEdit);
					break;
				case "3":
					userChange = view.getProductType();
					serviceLayer.editOrder(orderEditNumber, userChoice, userChange, dateForEdit);
					break;
				case "4":
					userChange = view.getArea();
					serviceLayer.editOrder(orderEditNumber, userChoice, userChange, dateForEdit);
					break;
				default:
					throw new UnsupportedOperationException("Unexpected result entered");
				}
				
				view.editOrderSuccessMessage();
				
				break;
				
			case "4":
				int orderDeleteNumber = view.getOrderNumber();
				String dateForDelete = view.getUserDate();
				serviceLayer.deleteOrder(orderDeleteNumber, false, dateForDelete);
				Order order = serviceLayer.getOrderByNumberAndDate(orderDeleteNumber, dateForDelete);
				view.displayOrder(order);
				String confirmation = view.getDeleteConfirmation();
				
				if(confirmation.equals("YES")) {
					serviceLayer.deleteOrder(orderDeleteNumber, true, dateForDelete);
					view.deleteOrderSuccessMessage();
				}
				else {
					
				}
			
				break;
			case "5":
				break;
			case "6":
				end = true;
				view.displayExitMessage();
				break;
			default:
				throw new UnsupportedOperationException("The input is unrecognised");
			}
		}
	}
}
