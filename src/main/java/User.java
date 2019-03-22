import java.util.ArrayList;
import java.sql.*;
import java.util.*;
public class User {
	
	public static enum UserType {Customer, Employee, Admin};

	private String userId;
	private String email;
	private String lastName;
	private String firstName;
	private UserType type;
	
	public User(String userId, String email, String lastName, String firstName, UserType type) {
		this.userId = userId;
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.type = type;
	}
	
	// basic constructor
	public User() {
		
	}
	
	public String getUserId() {
		return userId;
	}
	public String getEmail() {
		return email;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public UserType getUserType() {
		return type;
	}
	//public ArrayList<Order> getOrders() {
	//	return orders;
	//}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setUserType(UserType type) {
		this.type = type;
	}
	//public void setOrders(ArrayList<Order> orders) {
	//	this.orders = orders;
	//}
        static void getUsers(Connection connect) {
            try {
            ResultSet rs;
            Statement statement = connect.createStatement();    
            rs = statement.executeQuery(
                        "SELECT * from Users;"
                );
            System.out.println( 
                         "\temail"
                        + "\tlastName"
                        + "\tfirstName"
                        + "\ttype\n");
                
            while (rs.next()) {
                System.out.println(
                         "\t" + rs.getString("email")
                        + "\t" + rs.getString("lastName")
                        + "\t" + rs.getString("firstName")
                        + "\t" + rs.getString("type")
                );
            }
        }
            catch (Exception e) {
               System.out.println(e.getMessage());
           }
        }
        static void insertUser(Connection connect, String email, String password, String lastName, String firstName, String type) {
        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(
                    "INSERT INTO Users (email, password, lastName, firstName, type)\n" +
                    "VALUES ( \"" + email + 
                    "\", \"" + password +
                    "\", \"" + lastName +
                    "\", \"" + firstName +
                    "\", \"" + type +
                    "\");\n"
            );
            ResultSet rs;
            int itemID = 0;
 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    static void deleteUser(Connection connect, String email) {
        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(
                    "DELETE FROM Users\n" +
                    "WHERE email=" + email + ";\n"
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
