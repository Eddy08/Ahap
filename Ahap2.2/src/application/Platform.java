package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;

public class Platform {
	
	private static HashMap<Integer, Orders> orders = new HashMap<Integer, Orders>();
	private static HashMap<String, Employees> employees = new HashMap<String, Employees>();
	private static HashMap<Integer, Tables> tables = new HashMap<Integer, Tables>();
	private static Employees loggedIn; // stores  current employee logged into platform
	private static SceneController scene = new SceneController(); 

	public static void putOrder(Orders order, int order_id) {
		orders.put(order_id, order);

		Platform.getLoggedIn().addToLog("EMPLOYEE CREATED ORDER: " + order_id);
	}

	public static Orders getOrder(int orderID) {
		return orders.get(orderID);
	}

	public static HashMap<Integer, Orders> getAllOrders() {
		return orders;
	}

	public static int getTotal() {
		int total = 0;
		for (Orders order : getAllOrders().values()) {
			total += order.getOrderTotal();
		}
		return total;
	}

	public static void removeOrder(Integer orderID) {
		orders.remove(orderID);
		System.out.println("ORDER " + orderID + " REMOVED FROM STORE");
		

		Platform.getLoggedIn().addToLog("EMPLOYEE REMOVED ORDER: " + orderID);
	}

	public static Employees getEmployee(String username) {
		return employees.get(username);
	}
	

	public static HashMap<String, Employees> getAllEmployee() {
		return employees;
	}
	

	public static void putEmployee(Employees employee, String username) {
		employees.put(employee.getUsername(), employee);
	}

	public static void removeEmployee(String username) {
		employees.remove(username);
		System.out.println("EMPLOYEE " + username + " REMOVED FROM PLATFORM");
		
		Platform.getLoggedIn().addToLog("EMPLOYEE REMOVED EMPLOYEE: " + username);
	}

	public static Employees getLoggedIn() {
		return loggedIn;
	}

	public static void setLoggedIn(Employees loggedIn) {
		Platform.loggedIn = loggedIn;
	}

	public static void putTable(int table_id, Tables table) {
		tables.put(table_id, table);
	}

	public static Tables getTable(int table_id) {
		return tables.get(table_id);
	}

	public static HashMap<Integer, Tables> getAllTables() {
		return tables;
	}

	public static SceneController getScene() {
		return scene;
	}

	public static void setScene(SceneController scene) {
		Platform.scene = scene;
	}
}