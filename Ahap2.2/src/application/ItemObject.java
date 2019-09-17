package application;

public class ItemObject {
	
	private String quantity;
	private int price;
	private String name;

	public ItemObject(String name, int price, String quantity) {
		this.quantity = quantity;
		this.price = price;
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
