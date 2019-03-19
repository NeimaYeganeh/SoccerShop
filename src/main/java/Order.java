
public class Order {

	public static enum Status {Current, Awaiting, Processing, Failed, Shipped, Completed};
	
	private String orderId;
	private double price;
	private Status status;

	public Order() {
		
	}
	
	public String getOrderId() {
		return orderId;
	}
	public double getPrice() {
		return price;
	}
	public Status getStatus() {
		return status;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
