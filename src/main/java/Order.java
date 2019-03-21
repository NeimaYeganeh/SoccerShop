import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
        public static void viewOrders(Connection connect, Status status){
            
            try {
            ResultSet rs;
            String filter = null;
            int totalTags = 0;
            Statement statement = connect.createStatement();
            String query = "SELECT *" + 
                    "FROM Orders as O\n";
            if ( status != null )
            {
                query += "Where O.status = " + status.toString() + ";";
                System.out.println("hi + status.toString()");
            }
            else
                query += ";";
            
            rs = statement.executeQuery(query);
            
            System.out.println("orderID"
                        + "\t" + "price"
                        + "\t" + "status"
                        + "\t" + "lastName"
                        + "\t" + "firstName"
                        + "\t" + "email"
                        + "\t" + "isPickup"
                        + "\t" + "street"
                        + "\t" + "city"
                        + "\t" + "state"
                        + "\t" + "zip\n");
            while (rs.next()) {
                System.out.println(rs.getInt("orderID")
                        + "\t" + rs.getDouble("price")
                        + "\t" + rs.getString("status")
                        + "\t" + rs.getString("lastName")
                        + "\t" + rs.getString("firstName")
                        + "\t" + rs.getString("email")
                        + "\t" + rs.getInt("isPickup")
                        + "\t" + rs.getString("street")
                        + "\t" + rs.getString("city")
                        + "\t" + rs.getString("state")
                        + "\t" + rs.getString("zip")         
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            }
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

		if (pickup.equals("Y")) {
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
