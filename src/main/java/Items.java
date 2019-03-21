import java.sql.*;
import java.util.*;

public class Items {
    static void getItems(Connection connect, int sortOption) {
        String sortMethod = "";
        if (sortOption == 1) {
            sortMethod = "ORDER BY S.itemID ASC";
        } else if (sortOption == 2) {
            sortMethod = "ORDER BY S.itemID DESC";
        } else if (sortOption == 3) {
            sortMethod = "ORDER BY S.name ASC";
        } else if (sortOption == 4) {
            sortMethod = "ORDER BY S.name DESC";
        } else if (sortOption == 5) {
            sortMethod = "ORDER BY S.price ASC";
        } else if (sortOption == 6) {
            sortMethod = "ORDER BY S.price DESC";
        } else {
            System.out.println("Invalid sort");
            System.exit(1);
        }
        
        try {
            ResultSet rs;
            int totalTags = 0;
            Statement statement = connect.createStatement();
            rs = statement.executeQuery(
                    "SELECT COUNT(*) AS totalTags\n" + 
                    "FROM Tags T\n" +
                    "WHERE T.isSelected=true;\n"
            );
            while (rs.next()) {
                totalTags = rs.getInt("totalTags");
            }
            statement.executeUpdate(
                    "CREATE VIEW SelectedTags AS\n" +
                    "SELECT I.itemID, I.name, I.price, I.stock, I.description, T.tagID\n" + 
                    "FROM Items I, ItemTags IT, Tags T\n" +
                    "WHERE I.itemID=IT.itemID AND T.tagID=IT.tagID AND T.isSelected=true;\n"
            );
            statement.executeUpdate(     
                    "CREATE VIEW NumTags AS\n" + 
                    "SELECT S.itemID, COUNT(S.tagID) AS numTags\n" +
                    "FROM SelectedTags S\n" +
                    "GROUP BY S.itemID;\n"
            );
            if (totalTags > 0) {
                rs = statement.executeQuery(
                        "SELECT DISTINCT S.itemID, S.name, S.price, S.stock\n" + 
                        "FROM SelectedTags S, NumTags N\n" +
                        "WHERE N.numTags=" + totalTags + " AND N.itemID=S.itemID \n" +
                        sortMethod + ";\n"
                );
            } else {
                rs = statement.executeQuery(
                        "SELECT S.itemID, S.name, S.price, S.stock\n" + 
                        "FROM Items S\n" +
                        sortMethod + ";\n"
                );
            }
            System.out.format("%-10s %-30s %10s %10s\n", "ItemID", "Name", "Price", "Stock");
            while (rs.next()) {
                String itemID = rs.getString("itemID");
                String name = rs.getString("name");
                String price = rs.getString("price");
                String stock = rs.getString("stock");
                System.out.format("%-10s %-30s %10s %10s\n", itemID, name, price, stock);
            }
            statement.executeUpdate(
                    "DROP VIEW SelectedTags;\n"
            );
            statement.executeUpdate(
                    "DROP VIEW NumTags;\n"
            );
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void getItem(Connection connect, int itemID) {
        try {
            ResultSet rs;
            Statement statement = connect.createStatement();
            rs = statement.executeQuery(
                    "SELECT I.itemID, I.name, I.price, I.stock, I.description\n" + 
                    "FROM Items I\n" +
                    "WHERE I.itemID=" + itemID + ";\n"
            );
            while (rs.next()) {
                String name = rs.getString("name");
                String price = rs.getString("price");
                String stock = rs.getString("stock");
                String description = rs.getString("description");
                System.out.println(itemID
                        + "\t" + name
                        + "\t$" + price
                        + "\t" + stock
                        + "\t" + description
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void insertItem(Connection connect, String name, double price, int stock, String description, List<Integer> tags) {
        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(
                    "INSERT INTO Items (name, price, stock, description)\n" +
                    "VALUES (\"" + name +
                    "\", " + price +
                    ", " + stock +
                    ", \"" + description +
                    "\");\n"
            );
            ResultSet rs;
            int itemID = 0;
            rs = statement.executeQuery(
                    "SELECT MAX(I.itemID) AS itemID\n" + 
                    "FROM Items I;\n"
            );
            while (rs.next()) {
                itemID = rs.getInt("itemID");
            }
            for (int tagID : tags) {
                statement.executeUpdate(
                        "INSERT INTO ItemTags (itemID, tagID)\n" +
                        "VALUES (" + itemID + ", " + tagID + ");\n"
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void updateItemStock(Connection connect, int itemID, int stock) {
        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(
                    "UPDATE Items\n" + 
                    "SET stock=" + stock + "\n" +
                    "WHERE itemID=" + itemID + ";\n"
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void deleteItem(Connection connect, int itemID) {
        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(
                    "DELETE FROM Items\n" +
                    "WHERE itemID=" + itemID + ";\n"
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static Boolean hasItem(Connection connect, int itemID) {
        try {
            Statement statement = connect.createStatement();
            ResultSet rs;
            rs = statement.executeQuery(
                    "SELECT * FROM Items\n" +
                    "WHERE itemID=" + itemID + " AND stock>0;\n"
            );
            while (rs.next()) {
                if (itemID == rs.getInt("itemID")) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
