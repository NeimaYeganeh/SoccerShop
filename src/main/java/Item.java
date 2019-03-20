import java.util.ArrayList;

public class Item {
	
	private String itemId;
	private String name;
	private double price;
	private int stock;
	private String description;
	private ArrayList<ItemTag> tags;
	private int quantity;

	public Item() {
        this.quantity = 0;
	}

	public int getQuantity() {return quantity;}
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
	public ArrayList<ItemTag> getTags() {
		return this.tags;
	}

	public void setQuantity(int quantity) {this.quantity = quantity;}
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
	public void setTags(ArrayList<ItemTag> tags) {
		this.tags = tags;
	}
	public void addTag(ItemTag tag) {
		this.tags.add(tag);
	}

	@Override
	public boolean equals(Object o) {
		Item other;

		if (o == null)
			return false;
		if (o instanceof Item)
			other = (Item) o;
		else
			return false;
		if (itemId == other.itemId)
		    return true;
		return false;
	}
}
