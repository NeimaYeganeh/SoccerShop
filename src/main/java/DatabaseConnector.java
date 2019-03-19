import java.sql.*;

public class DatabaseConnector {
    static Connection connect;
    public static final String DB_ENDPOINT = "ambari-head.csc.calpoly.edu/jccho";
    
    public static void main(String[] args) {
        
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://" + DatabaseConnector.DB_ENDPOINT;
            String username = "jccho";
            String password = "012912454";
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            
            Items.getItems(connect, 1);
            Items.getItems(connect, 2);
            Items.getItems(connect, 3);
            Items.getItems(connect, 4);
            Items.getItems(connect, 5);
            Items.getItems(connect, 6);
            Tags.getTags(connect);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
