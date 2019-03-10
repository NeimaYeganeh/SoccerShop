import java.sql.*; 
import java.util.*; 

class SoccerShop
{ 
	static Connection connect;
   public static void main(String args[])
   {
	   try {
		   Class.forName("om.mysql.jdbc.Driver");
		   	   //ambari-head.csc.calpoly.edu

		   connect = DriverManager.getConnection("jdbc:mysl//localhost:3306/testdb?user=root&password=...");
		   
		   Statement statement = connect.createStatement();
		   ResultSet rs = statement.executeQuery( "SELECT * FROM BOATS");
		   while (rs.next()) { 
		   	String boatName = rs.getString(2);
		   	System.out.println("Boat name= " + boatName);
		   }
	   } catch (Exception e) {System.out.println(e.getMessage());}
   }
}
