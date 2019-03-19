import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class DatabaseConnector {
	
	public static final String DB_ENDPOINT = "finalproject365.czbltj2ae4tp.us-west-1.rds.amazonaws.com"; 

	public static Connection getConnection() {
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://" + DatabaseConnector.DB_ENDPOINT;
			String username = "admin";
			String password = "password";
			Class.forName(driver);

			Connection con = DriverManager.getConnection(url, username, password);

			return con;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	// fetch from DB
	public static User getUser(String email) {
		
		User user = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			con = getConnection();
			String stmtString = "SELECT * FROM Users WHERE Users.email=?";
			stmt = con.prepareStatement(stmtString);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
		
			while (rs.next()) {
				
				String userId = rs.getString("userID");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				User.UserType type = User.UserType.valueOf(rs.getString("type"));
				
				user = new User(userId, email, lastName, firstName, type);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		return user;
	}
	
//	// should refactor to do password stuff
//	public static void storeUser(User user) {
//		
//		Connection con = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		try {
//			
//			con = getConnection();
//			String stmtString = "SELECT 1 FROM Users WHERE Users.userID=?";
//			stmt = con.prepareStatement(stmtString);
//			stmt.setString(1, user.getUserId());
//			rs = stmt.executeQuery();
//		
//			if (rs.next() && rs.getInt(1)==1) {
//				return;
//			}
//			else {
//				stmtString = "INSERT INTO Users("
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				stmt.close();
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}	
//		}
//	}
	
	public static ArrayList<Order> getOrders(String userId) {
		
		ArrayList<Order> orders = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;;
		ResultSet rs = null;
		
		try {
			
			con = getConnection();
			String stmtString = "SELECT * FROM Orders WHERE Orders.userID=?";
			stmt = con.prepareStatement(stmtString);
			stmt.setString(1, userId);
			rs = stmt.executeQuery();
		
			while (rs.next()) {
				
				Order order = new Order();
				order.setOrderId(rs.getString("orderID"));
				order.setPrice(rs.getDouble("price"));
				order.setStatus(Order.Status.valueOf(rs.getString("status")));
				orders.add(order);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return orders;
	}

	// updates DB with new Order; Sets orderID field in Order
	public static Order storeOrder(Order order) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			
			con = getConnection();

			String colString = "price,status,lastName,firstName,email,isPickup,street,city,state,zip";
			String stmtString = String.format("INSERT INTO Orders(%s) VALUES (?,?,?,?,?,?,?,?,?,?)", colString);

			stmt = con.prepareStatement(stmtString, Statement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, order.getPrice());
			stmt.setString(2, order.getStatus().toString());
			stmt.setString(3, order.getLastName());
			stmt.setString(4, order.getFirstName());
			stmt.setString(5, order.getEmail());
            stmt.setBoolean(6, order.getPickup());
            stmt.setString(7, order.getStreet());
            stmt.setString(8, order.getCity());
            stmt.setString(9, order.getState());
            stmt.setString(10, order.getZip());
			stmt.executeUpdate();
			
			// update the Order object with the generated OrderID
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next())
				order.setOrderId(rs.getString("orderID"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return order;
	}

	public static void newItem(Item item) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			
			con = getConnection();
			String stmtString = "INSERT INTO Items(userID, name, price, stock, description) VALUES (?,?,?,?,?)";
			stmt = con.prepareStatement(stmtString, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, item.getItemId());
			stmt.setString(2, item.getName());
			stmt.setDouble(3, item.getPrice());
			stmt.setInt(4, item.getStock());
			stmt.setString(5, item.getDescription());

			stmt.executeUpdate();
			
			// update the Order object with the generated OrderID
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next())
				item.setItemId(rs.getString("itemID"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}

	public static int getStock(Item item) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int stock = 0;
		
		try {

			con = getConnection();
			String stmtString = "SELECT stock FROM Items WHERE itemID=?";
			stmt = con.prepareStatement(stmtString);
			stmt.setString(1,  item.getItemId());
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				stock = rs.getInt("stock");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return stock;
	}
	
	// updates item stock in db if possible
	// returns false if not enough stock to complete transaction
	public static void purchaseItem(Item item) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		int quantity = item.getQuantity();

		try {
			con = getConnection();
			String stmtString = "UPDATE Items SET stock=? WHERE itemID=?";
			stmt = con.prepareStatement(stmtString);
			stmt.setInt(1, getStock(item)-quantity);
			stmt.setString(2,  item.getItemId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
}
