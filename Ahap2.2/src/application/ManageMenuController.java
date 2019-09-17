package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class ManageMenuController implements Initializable {
	
	@FXML private TextField txtItem, txtPrice;
	@FXML private TableView<ItemObject> tvItems;
	@FXML private TableColumn<ItemObject, String> itemName;
	@FXML private TableColumn<ItemObject, Integer> itemPrice;
	@FXML private Button btnAdd;
	@FXML private Button btnDelete;
	

	public ObservableList<ItemObject> items = FXCollections.observableArrayList(Items.getItemObjects().values());

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		btnDelete.setDisable(true);
		
		tvItems.setItems(items);
		
		itemName.setCellValueFactory(new PropertyValueFactory<ItemObject, String>("name"));
		itemPrice.setCellValueFactory(new PropertyValueFactory<ItemObject, Integer>("price"));
		
	}

	public void addNewItem(ActionEvent event) throws Exception {
		
		try {

		String item = txtItem.getText();

		if (item.isEmpty() ) {
			throw new Exception();
		}

		int price = Integer.parseInt(txtPrice.getText());

		Items.addItem(item, price);

		tvItems.getItems().add(Items.getItemObjects().get(item));
		
		} catch (NumberFormatException e) {
			System.out.println("Please enter valid integer");
		} catch (Exception e) {
			System.out.println("Make sure Item TextField is not blank.");
		}
		
		
	}

	public void deleteItem(ActionEvent event) {

		ObservableList<ItemObject> allItems;

		ItemObject itemSelected;

		allItems = tvItems.getItems();

		itemSelected = tvItems.getSelectionModel().getSelectedItem();
				

		allItems.remove(itemSelected);

		Items.removeItem(itemSelected.getName());
		
	}

	public void home(ActionEvent event) throws IOException {

		Platform.getScene().home();
		
	}

}
