
public class Order {

	public static enum Status {};
	
	private String orderId;
	private String userId;
	private double price;
	private Status status;
	
	public Order(String orderId, String userId, double price, Status status) {
		this.orderId = orderId;
		this.userId = userId;
		this.price = price;
		this.status = status;
	}
	
	public Order() {
		
	}
	
	public String getOrderId() {
		return orderId;
	}
	public String getUserId() {
		return userId;
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
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
