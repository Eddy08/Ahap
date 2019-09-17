package application;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;




public class HomepageController implements Initializable {

	@FXML private Label lblPlatformTotal;
	

	@FXML private Button btn1 = new Button("1");
	@FXML private Button btn2 = new Button("2");
	@FXML private Button btn3 = new Button("3");
	@FXML private Button btn4 = new Button("4");
	@FXML private Button btn5 = new Button("5");
	@FXML private Button btn6 = new Button("6");
	@FXML private Button btn7 = new Button("7");
	@FXML private Button btn8 = new Button("8");
	@FXML private Button btn9 = new Button("9");
	
	@FXML private Button menuManager = new Button();
	@FXML private Button staffManager = new Button();
	

	public ArrayList<Button> allButtons = new ArrayList<Button>();
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {


		lblPlatformTotal.setText("Rs." + Platform.getTotal() + ".00");
		addButtons();
		setButtonColour(allButtons);

		if (Platform.getLoggedIn().getEmployeeType().equals("Staff")) {
			menuManager.setDisable(true);
			staffManager.setDisable(true);
		}

		
	}
	


	public void setButtonColour(ArrayList<Button> allButtons) {
		
		Collection<Tables> allTables = Platform.getAllTables().values();
		ArrayList<Integer> availableTables = new ArrayList<Integer>();
		
		for (Tables table : allTables ) {
			if ( table.getOrderID() == 0) {
				availableTables.add(table.tableNumber);
			}
		}
		
		for (Button button : allButtons ) {
			if ( !availableTables.contains(Integer.parseInt(button.getText()))) {
				button.setStyle("-fx-color: #BED347;}");
			}
		}
	}

	public void addButtons() {
		allButtons.removeAll(allButtons);
		allButtons.add(btn1);
		allButtons.add(btn2);
		allButtons.add(btn3);
		allButtons.add(btn4);
		allButtons.add(btn5);
		allButtons.add(btn6);
		allButtons.add(btn7);
		allButtons.add(btn8);
		allButtons.add(btn9);	
	}
	

	public void modifyOrder(ActionEvent event) throws Exception {
		
		Tables table;
		Orders order;
		

		String selectedButton = event.getSource().toString().substring(35, 36);
		
		try {
			
			table = Platform.getTable(Integer.parseInt(selectedButton));
			order = Platform.getOrder(table.getOrderID());
			
			Variables.setOrder(order.getOrderID());
			goToModifyPage();
			order.displayOrder();
			
		} catch (Exception e) {
			System.out.println("This table is currently empty.");
			goToOrder();
		}
	}
	

	public void goToModifyPage() throws IOException {
	
		Stage primaryStage = Main.getStage();
		FXMLLoader loader =  new FXMLLoader();
		Parent root = loader.load(getClass().getResource("/application/ModifyOrder.fxml").openStream());
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		
	}
	

	public void goToOrder() throws IOException {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewOrder.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void newOrder(ActionEvent event) throws IOException {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewMenuItem.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	

	public void goToOrder(ActionEvent event) throws IOException {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewOrder.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}


	public void goToEmployeeManager(ActionEvent event) throws IOException {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/ManageEmployees.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	

	public void logout(ActionEvent event) throws IOException {

		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
