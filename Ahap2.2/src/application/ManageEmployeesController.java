package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManageEmployeesController implements Initializable {
	
	@FXML private TableView<Employees> tvEmployeeTable;
	@FXML private TableColumn<Employees, Integer> id;
	@FXML private TableColumn<Employees, String> firstname;
	@FXML private TableColumn<Employees, String> lastname;
	@FXML private TableColumn<Employees, String> username;
	@FXML private TableColumn<Employees, String> employeeType;
	@FXML private TextArea log;
	
	public static Stage window = new Stage();
	

	public ObservableList<Employees> employees = FXCollections.observableArrayList(Platform.getAllEmployee().values());


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tvEmployeeTable.setItems(employees);
		
		id.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("employeeNumber"));
		firstname.setCellValueFactory(new PropertyValueFactory<Employees, String>("firstName"));
		lastname.setCellValueFactory(new PropertyValueFactory<Employees, String>("lastName"));
		username.setCellValueFactory(new PropertyValueFactory<Employees, String>("username"));
		employeeType.setCellValueFactory(new PropertyValueFactory<Employees, String>("employeeType"));

	}

	public void deleteEmployee(ActionEvent event) {
		

		ObservableList<Employees> allEmployees;
		

		Employees employeeSelected;
		
		allEmployees = tvEmployeeTable.getItems();
		
		employeeSelected = tvEmployeeTable.getSelectionModel().getSelectedItem();

		allEmployees.remove(employeeSelected);
		

		Platform.removeEmployee(employeeSelected.getUsername());
		
	}

	public void Home(ActionEvent event) throws IOException {
		

		Platform.getScene().home();
		
	}

	public void goToNewEmployeePage(ActionEvent event) throws IOException {
		
		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewEmployee.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	
}
