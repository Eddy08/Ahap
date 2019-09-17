package application;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Orders {

	
	private static int orderCount;
	private int orderID;
	private int tableNumber;
	private String comments = "";
	private String timeOfOrder;
	private String itemOrderedString;
	private int orderTotal;
	private int orderTotalObjects;
	

	private HashMap<String, Integer> orderContents = new HashMap<String, Integer>();

	private ArrayList<ItemObject> orderItemObjects = new ArrayList<ItemObject>();

	public Orders(int tableNumber) {
		
		this.orderID = ++orderCount;
		this.tableNumber = tableNumber;
		this.setTime();
		System.out.println("ORDER " + orderID + " CREATED");
		}

	public void addItemBuffer(ItemObject item) {
		orderItemObjects.add(item);

		itemsOrderedString();
	}

	public void addMultipleItemBuffer(ArrayList<ItemObject> items) {
		for (ItemObject item : items ) {
			addItemBuffer(item);
		}
	}

	public void removeItemBuffer(ItemObject item) {
		orderItemObjects.remove(item);
		// update the order string when item removed
		itemsOrderedString();
	}
	

	public ArrayList<ItemObject> orderItemObjects() {
		return this.orderItemObjects;
	}
	

	public int getOrderTotalObjects() {
		int total = 0;
		for (ItemObject item : orderItemObjects) {
			total += item.getPrice();
		}
		
		this.orderTotalObjects = total;
		return orderTotalObjects;
	}

	public void addOrderItem(String item, int quantity) {

		if (this.orderContents.containsKey(item) == true) {
			int currentQuantity = this.orderContents.get(item);
			this.orderContents.put(item, currentQuantity + quantity);
		}

		else { this.orderContents.put(item, quantity); }
	}

	public void addMultipleOrderItems(HashMap<String, Integer> order) {
		
		for (Map.Entry<String, Integer> pair : order.entrySet()) {
			String foodItem = pair.getKey();
			int quantityToAdd = pair.getValue();
			this.addOrderItem(foodItem, quantityToAdd);
			
		}
	}


	public void displayOrder() {
		System.out.println("---------------------------");
		for (Map.Entry<String, Integer> entry : this.orderContents.entrySet()) {
			System.out.println(entry.getKey() + " | " + "x " +  entry.getValue() + " | Rs." + Items.getItemPrice(entry.getKey()) );
		}
		System.out.println("---------------------------");
		System.out.println("ORDER TIME: " + this.timeOfOrder);
		System.out.println("TABLE: " + this.tableNumber);
		System.out.println("COMMENTS: " + this.comments);
		System.out.println("---------------------------");
		System.out.println("ORDER TOTAL: Rs." + this.getOrderTotal());
		System.out.println("THANK YOU FOR VISITING US :)");
		System.out.println("---------------------------");
	}

	public String orderDetails() {
		
		String details = "RESTAURANT MANAGER \n";
		
		for (Map.Entry<String, Integer> entry : this.orderContents.entrySet()) {
			details += entry.getKey() + " | " + "x " +  entry.getValue() + " | Rs." + Items.getItemPrice(entry.getKey()) + "\n";
		}
		
		details += "ORDER TIME: " + this.timeOfOrder + "\n";
		details += "TABLE: " + this.tableNumber + "\n";
		details += "COMMENTS: " + this.comments + "\n";
		details += "ORDER TOTAL: Rs." + this.getOrderTotal();
		
		return details;
	}

	public String getComments() {
		return comments;
	}

	public void addComments(String comment) {
		this.comments = comment;
	}

	public int getOrderID() { 
		return this.orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getOrderTotal() {
		int total = 0;
		for (ItemObject item : orderItemObjects) {
			total += item.getPrice();
		}
		
		this.orderTotal = total;
		return orderTotal;
	}

	private void setTime() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.timeOfOrder = dateFormat.format(date);
	}

	public void setTimeOfOrder(String timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}

	public String getTimeOfOrder() {
		return timeOfOrder;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	private void itemsOrderedString() {
		String itemString = "";
		for (ItemObject item : orderItemObjects) {
			itemString +=  item.getName() + " ";
		}
		this.itemOrderedString = itemString;	
	}

	public String getItemOrderedString() {
		return itemOrderedString;
	}


}