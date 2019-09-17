package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManageOrderController implements Initializable  {

	private static Stage window;
	@FXML private TableView<Orders> tvOrderTable;
	@FXML private TableColumn<Orders, Integer> id;
	@FXML private TableColumn<Orders, Integer> tableNumber;
	@FXML private TableColumn<Orders, String> date;
	@FXML private TableColumn<Orders, String> orderTotal;
	@FXML private TableColumn<Orders, String> itemsOrdered;
	@FXML private Button delete;
	@FXML private Button modify;
	@FXML private TextField filterField;
	private int sourceIndex;
	private int visibleIndex;
	SortedList<Orders> sortedData;
	

	public ObservableList<Orders> orders = FXCollections.observableArrayList(Platform.getAllOrders().values());
	public FilteredList<Orders> filteredData = new FilteredList<>(orders, p -> true);
		
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		


		tvOrderTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		tvOrderTable.getSelectionModel().selectedItemProperty().addListener((order) -> {

			if ( !tvOrderTable.getSelectionModel().isEmpty() ) {
				modify.setDisable(false);
				delete.setDisable(false);
			}

			else {
				modify.setDisable(true);
				delete.setDisable(true);
			}
		});
		

		tvOrderTable.setItems(orders);
		

		id.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("orderID"));
		tableNumber.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("tableNumber"));
		date.setCellValueFactory(new PropertyValueFactory<Orders, String>("timeOfOrder"));
		orderTotal.setCellValueFactory(new PropertyValueFactory<Orders, String>("orderTotalObjects"));
		itemsOrdered.setCellValueFactory(new PropertyValueFactory<Orders, String>("itemOrderedString"));
				 

		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
	            filteredData.setPredicate(order -> {

	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }

	                String lowerCaseFilter = newValue.toLowerCase();

	                if ((order.getTableNumber() + "").contains(lowerCaseFilter)) {
	                    return true;
	                } 
	               
	                else if ((order.getOrderID() + "").toLowerCase().contains(lowerCaseFilter)) {
	                    return true;
	                } 
	                
	                else if ((order.getItemOrderedString()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matched items in order
	                }
	                
	                return false; // Does not match.
	            });
	        });
		 
		 sortedData = new SortedList<>(filteredData);
		 sortedData.comparatorProperty().bind(tvOrderTable.comparatorProperty());		 
		 tvOrderTable.setItems(sortedData);

	}
	

	public void deleteConformation(ActionEvent event ) throws IOException {
		
		visibleIndex = tvOrderTable.getSelectionModel().getSelectedIndex();
		sourceIndex = sortedData.getSourceIndexFor(orders, visibleIndex);
		
		Variables.setMasterData(orders);
		Variables.setSourceIndex(sourceIndex);
		Variables.setVisibleIndex(visibleIndex);
		
		Variables.setOrderSelected(tvOrderTable.getSelectionModel().getSelectedItem());
		Variables.setAllOrders(tvOrderTable);
		
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/application/ManageConfirmBox.fxml"));
		Scene scene = new Scene(root, 300, 200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}

	public void modifyOrder(ActionEvent event) throws IOException {
		
		//create order object 
		Orders orderSelected;
				
		// put the current order selected into this variable 
		orderSelected = tvOrderTable.getSelectionModel().getSelectedItem();
				
		Variables.setOrder(orderSelected.getOrderID());
		
		Stage primaryStage = Main.getStage();
		FXMLLoader loader =  new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/application/ModifyOrder.fxml").openStream());
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		
	}

	public void closeOrder(ActionEvent event) {
		
		//create order object 
		Orders orderSelected;
						
		// put the current order selected into this variable 
		orderSelected = tvOrderTable.getSelectionModel().getSelectedItem();
		
		// set the order table to 0 
		if (orderSelected.getTableNumber() != 0) {
		Platform.getTable(orderSelected.getTableNumber()).setOrderID(0);
		}
		
		orderSelected.setTableNumber(0);
		
		// refreshes the table to update the available ones
		tvOrderTable.refresh();
		
	}


	public static Stage getWindow() {
		return window;
	}

	

	public void Home(ActionEvent event) throws IOException {
		
		// go to homepage 
		Platform.getScene().home();
	}

	
}
