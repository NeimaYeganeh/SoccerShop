import java.sql.*;
import java.util.*;

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
            
            Tags.clearTags(connect);
            Items.getItems(connect, 1);
            System.out.println("");
            //Items.insertItem(connect, "Basketball", 399, 3, "Orange", new ArrayList<Integer>(Arrays.asList(21, 20)));
            //Items.deleteItem(connect, 30);
            Items.getItems(connect, 1);
            System.out.println("");
            
            Tags.selectTag(connect, 21);
            Tags.getTags(connect);
            System.out.println("");
            Tags.selectTag(connect, 20);
            Tags.getTags(connect);
            System.out.println("");
            
            Items.getItems(connect, 1);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
