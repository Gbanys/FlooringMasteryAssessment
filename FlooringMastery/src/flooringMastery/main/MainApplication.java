package flooringMastery.main;

import flooringMastery.controller.FlooringMasteryController;
import flooringMastery.dao.FlooringMasteryDao;
import flooringMastery.dao.FlooringMasteryDaoImpl;
import flooringMastery.servicelayer.FlooringMasteryServiceLayer;
import flooringMastery.servicelayer.FlooringMasteryServiceLayerImpl;
import flooringMastery.view.FlooringMasteryView;
import flooringMastery.view.UserIO;
import flooringMastery.view.UserIOImpl;

public class MainApplication {

	public static void main(String[] args) throws Exception {

		UserIO io = new UserIOImpl();
		FlooringMasteryView view = new FlooringMasteryView(io);
		FlooringMasteryDao dao = new FlooringMasteryDaoImpl();
		FlooringMasteryServiceLayer service = new FlooringMasteryServiceLayerImpl(dao);
		FlooringMasteryController controller = new FlooringMasteryController(view, service);
		controller.run();
	}

}
