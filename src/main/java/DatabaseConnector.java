import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {

	public static Connection getConnection() {
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://" + SoccerShopConstants.DB_ENDPOINT;
			String username = "admin";
			String password = "password";
			Class.forName(driver);

			Connection connection = DriverManager.getConnection(url, username, password);

			return connection;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	// fetch from DB
	public static User getUser(String email) {
		
		User user = null;
		Connection con = null;;
		PreparedStatement stmt = null;;
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
	
	// store in DB
	public static void storeUser(User user) {
	
	}
}
