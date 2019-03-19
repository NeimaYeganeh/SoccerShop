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
            System.out.println("");
            Items.getItem(connect, 3);
            Items.updateItemStock(connect, 3, 10);
            Items.getItem(connect, 3);
            System.out.println("");
            //Items.insertItem(connect, "Basketball", 399, 3, "Orange");
            //Items.deleteItem(connect, 27);
            Items.getItems(connect, 1);
            System.out.println("");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
