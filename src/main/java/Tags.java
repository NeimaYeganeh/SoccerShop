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
    /*
    static void clearTags(Connection connect) {
        try {    
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT *\n" +
                    "FROM Tags T\n"
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
    */
}
