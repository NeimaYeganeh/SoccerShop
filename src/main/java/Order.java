import java.util.Scanner;

public class Order {

	public static enum Status {Current, Awaiting, Processing, Failed, Shipped, Completed}

	private String orderId;
	private double price;
	private Status status;

	private String lastName;
	private String firstName;
	private String email;
	private String street;
	private String city;
	private String state;
	private String zip;
	private boolean isPickup;

	public Order() {
		setOrderDetails();
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
	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	public boolean getPickup() {
		return isPickup;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
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

	public void setOrderDetails() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Please fill out the following information...");

		System.out.print("First Name: ");
		firstName = sc.nextLine().trim();
		System.out.print("Last Name: ");
		lastName = sc.nextLine().trim();
		System.out.print("Email: ");
		email = sc.nextLine().trim();

		System.out.println("Do you want store pickup (Y/N) ?");
		String pickup = sc.nextLine().trim().toUpperCase();

		if (pickup == "Y") {
			isPickup = true;
		}
		else {
			isPickup = false;
			System.out.println("Please provide the shipping information...");
			System.out.print("Street Address: ");
			street = sc.nextLine().trim();
			System.out.print("City: ");
			city = sc.nextLine().trim();
			System.out.println("State: ");
			state = sc.nextLine().trim();
			System.out.println("Zip Code: ");
			zip = sc.nextLine().trim();
		}
	}


}
