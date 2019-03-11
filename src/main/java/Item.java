
public class Item {
	
	private String itemId;
	private String name;
	private double price;
	private int stock;
	private String description;
	
	public Item() {
		
	}
	
	public String getItemId() {
		return this.itemId;
	}
	public String getName() {
		return this.name;
	}
	public double getPrice() {
		return this.price;
	}
	public int getStock() {
		return this.stock;
	}
	public String getDescription() {
		return this.description;
	}
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
