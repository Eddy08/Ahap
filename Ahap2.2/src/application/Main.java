package application;

import java.io.IOException;


import java.util.Random;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;


public class Main extends Application {
	

	private static Stage stage;


	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Main.stage = primaryStage;
		
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static Stage getStage() {
		return stage;
	}
	public static void main(String[] args) throws IOException {


		initialise();
		launch(args);
	}

	public static void initialise() {

		


		Employees test = new Employees("Manager", "Harsh", "Bhardwaj", "harsh", "123");
		Platform.setLoggedIn(test);
		Employees manager1 = new Employees("Staff", "Priyanshu", "Parmar", "pp", "pp");
		Employees manager2 = new Employees("Staff", "Ankit", "Paul", "ap", "12345");
		Employees aditya = new Employees("Staff", "Aditya", "Garg", "adi", "adi");

		Platform.putEmployee(manager2, manager2.getUsername());
		Platform.putEmployee(test, test.getUsername());
        Platform.putEmployee(manager1, manager1.getUsername());
		Platform.putEmployee(aditya, aditya.getUsername());

		


		Items.addItem("Aloo Parantha", 20);
		Items.addItem("Banana Shake", 15);
		Items.addItem("Orbit", 10);
		Items.addItem("Carrot Juice", 50);
		Items.addItem("Panner Parantha", 25);
		Items.addItem("Panner Roll", 60);
		Items.addItem("Omelete", 25);
		Items.addItem("Strawberry Shake", 30);
		Items.addItem("Chocolate Milkshake", 15);

		
		for (int i = 0; i < 9; i++) {
			Tables table = new Tables();
			Platform.putTable(table.tableNumber, table);
		}

		
		Random rand = new Random();
		
		/*for (int i = 0; i < 5; i++) {
			

			Orders newOrder = new Orders(i+1);
			

			newOrder.addItemBuffer(Items.itemObjects.get(Items.itemObjects.keySet().toArray()[rand.nextInt(8)]));
			newOrder.addItemBuffer(Items.itemObjects.get(Items.itemObjects.keySet().toArray()[rand.nextInt(8)]));
			newOrder.addItemBuffer(Items.itemObjects.get(Items.itemObjects.keySet().toArray()[rand.nextInt(8)]));
			newOrder.addItemBuffer(Items.itemObjects.get(Items.itemObjects.keySet().toArray()[rand.nextInt(8)]));
			Platform.putOrder(newOrder, newOrder.getOrderID());
			Platform.getTable(newOrder.getOrderID()).orderID = newOrder.getOrderID();
		}*/
	}
}
