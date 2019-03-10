import java.sql.Connection;
import java.sql.DriverManager;

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
}
