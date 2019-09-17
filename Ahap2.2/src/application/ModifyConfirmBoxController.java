package application;

import java.io.IOException;

import javafx.event.ActionEvent;



public class ModifyConfirmBoxController  {
	

	public void deleteOrderConformation(ActionEvent event) throws IOException  {
				
		Orders orderSelected = Variables.getOrderSelected();
		

		if (orderSelected.getTableNumber() != 0) {
		Platform.getTable(orderSelected.getTableNumber()).setOrderID(0);
		}

		orderSelected.setTableNumber(0);

		Platform.removeOrder(orderSelected.getOrderID());

		ModifyOrderController.getWindow().close();

		Platform.getScene().home();
	
	}

	public void cancel(ActionEvent event) {
		ModifyOrderController.getWindow().close();
	}
	
}