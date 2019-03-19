import java.sql.*;

public class Tags {
    static void getTags(Connection connect) {
        try {    
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT *\n" +
                    "FROM Tags T;\n"
            );
            while (rs.next()) {
                String tagID = rs.getString("tagID");
                String name = rs.getString("name");
                String isSelected = rs.getString("isSelected");
                System.out.println(tagID
                        + "\t" + name
                        + "\t" + isSelected
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void selectTag(Connection connect, int tagID) {
        try {
            ResultSet rs;
            Boolean isSelected = false;
            Statement statement = connect.createStatement();
            rs = statement.executeQuery(
                    "SELECT T.isSelected\n" + 
                    "FROM Tags T\n" +
                    "WHERE T.tagID=" + tagID + ";\n"
            );
            while (rs.next()) {
                isSelected = rs.getBoolean("isSelected");
            }
            statement.executeUpdate(
                    "UPDATE Tags T\n" +
                    "SET T.isSelected=" + !isSelected + "\n" +
                    "WHERE T.tagID=" + tagID +";\n"
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
  
    static void clearTags(Connection connect) {
        try {    
            Statement statement = connect.createStatement();
            statement.executeUpdate(
                    "UPDATE Tags\n" +
                    "SET isSelected=false\n"
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
