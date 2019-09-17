package application;

import java.util.HashMap;


public class Items {

	
	public static HashMap<String, Integer> items = new HashMap<String, Integer>();
	public static HashMap<String, ItemObject> itemObjects = new HashMap<String, ItemObject>();
	public static void addItem(String name, int price) {
		items.put(name, price);
		System.out.println("ITEM ADDED: " + name + "Rs." + price);


		itemObjects.put(name, new ItemObject(name, price, "1"));
	}

	public static void removeItem(String name) {
		items.remove(name);
		

		itemObjects.remove(name);
		
		System.out.println("ITEM REMOVED: " + name);
	}
	

	public static HashMap<String, ItemObject> getItemObjects() {
		return itemObjects;
	}

	public static int getItemPrice(String name) {
		return items.get(name);
	}
	
}