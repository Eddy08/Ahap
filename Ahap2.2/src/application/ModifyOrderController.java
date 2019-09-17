package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModifyOrderController implements Initializable {
	
	private static Stage window;
	private int orderID = Variables.getOrder();
	private Orders ordermate = Platform.getOrder(orderID);

	@FXML private Label lblOrderNumber, lblTotal;
	@FXML private TableView<ItemObject> tvItemTable;
	@FXML private TableColumn<ItemObject, String> item;
	@FXML private TableColumn<ItemObject, Integer> price;
	@FXML private TableColumn<ItemObject, String> quantity;
	@FXML private TextArea txtComments;
	@FXML private ComboBox<String> cbItems;
	@FXML private TextField txtQuantity;
	
	public HashMap<String, Integer> orderList2 = new HashMap<String, Integer>();

	public ObservableList<ItemObject> itemList = FXCollections.observableArrayList(Platform.getOrder(orderID).orderItemObjects());

	public ArrayList<ItemObject> exprimentOrderList = ordermate.orderItemObjects();

	ObservableList<String> dropdownList = FXCollections.observableArrayList(Items.items.keySet());

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lblOrderNumber.setText("Order " + orderID);
		lblOrderNumber.setVisible(true);
		

		lblTotal.setText("Rs." + ordermate.getOrderTotalObjects() + ".00" );

		tvItemTable.setItems(itemList);

		item.setCellValueFactory(new PropertyValueFactory<ItemObject, String>("name"));
		price.setCellValueFactory(new PropertyValueFactory<ItemObject, Integer>("price"));
		quantity.setCellValueFactory(new PropertyValueFactory<ItemObject, String>("quantity"));
		
		// Display comments and special messages
		txtComments.setText(Platform.getOrder(orderID).getComments());
		
		// add all items (foods) to the combo list
		cbItems.setItems(dropdownList);
		txtQuantity.setText("1");
		txtQuantity.setEditable(true);
		cbItems.getSelectionModel().selectFirst();
		

	}
	

	public void deleteItem(ActionEvent event) {
		

		Orders order = Platform.getOrder(orderID);
		
		// create a list to hold all of the Items 
		ObservableList<ItemObject> allItems;
				
		//create order object 
		ItemObject itemSelected;
				
		// get all of the current orders in the TableView
		allItems = tvItemTable.getItems();
				
		// put the current order selected into this variable 
		itemSelected = tvItemTable.getSelectionModel().getSelectedItem();
				
		// remove this order from the table view 
		allItems.remove(itemSelected);

		order.removeItemBuffer(itemSelected);

		lblTotal.setText("Rs." + order.getOrderTotalObjects() + ".00" );
	}

	public void Home(ActionEvent event) throws IOException {
		

		Platform.getOrder(orderID).addComments(txtComments.getText());

		Platform.getScene().home();
	
	}

	public void deleteConformation(ActionEvent event) throws IOException {
		
		Variables.setOrderSelected(ordermate);

		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/application/ModifyConfirmBox.fxml"));
		Scene scene = new Scene(root, 300, 200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}

	public static Stage getWindow() {
		return window;	
	}

	public void addItem(ActionEvent event) {

		String text = cbItems.getSelectionModel().getSelectedItem();
		int quantity2 = Integer.parseInt(txtQuantity.getText()); //hashmap version
				
		
		if (orderList2.containsKey(text)) {
			orderList2.put(text, orderList2.get(text) + quantity2);
		}
		
		else {
			orderList2.put(text, quantity2);
		}
		
		// Allow user to see what has been added to their order
		ItemObject item = new ItemObject(text, Items.getItemPrice(text), quantity2+"");

		// add the item to the order
		exprimentOrderList.add(item);

		// update the total label
		lblTotal.setText("Rs." + ordermate.getOrderTotalObjects() + ".00");
	
		// add the selected item to the TableView
		tvItemTable.getItems().add(item);
		
	}
	
	
}
