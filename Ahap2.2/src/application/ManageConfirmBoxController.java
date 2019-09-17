package application;

import javafx.event.ActionEvent;

public class ManageConfirmBoxController {
	
	

	public void deleteOrderConformation(ActionEvent event)  {
				

		Orders orderSelected;
		

		orderSelected = Variables.getOrderSelected();
		

		Variables.getMasterData().remove(Variables.getSourceIndex());

		if (orderSelected.getTableNumber() != 0) {
		Platform.getTable(orderSelected.getTableNumber()).setOrderID(0);
		}
		

		orderSelected.setTableNumber(0);


		Platform.removeOrder(orderSelected.getOrderID());
		

		ManageOrderController.getWindow().close();
	
	}

	public void cancel(ActionEvent event) {
		ManageOrderController.getWindow().close();
	}
	
}