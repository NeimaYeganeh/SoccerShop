
import static DatabaseConnector.getConnection;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.sql.*;

public class SoccerShop {

    public enum ShopState {
        START, USAGEMESSAGE, EXIT, STORE, CART, DONE, LOGIN, VIEWITEMS
    }

    private ShopState state;

	public SoccerShop() {
	    this.state = ShopState.START;
    }

    // Main UI for the SoccerShop
    public void launchCLI() {

        Scanner sc = new Scanner(System.in);
        String input = null;
        String response = null;

        /*
        *   main control flow of program
        *   quits program when "Exit" command entered
        */
        
        while (true) {

            response = doAction(state); // executes the action
            System.out.print(response); // displays next prompt
            input = sc.nextLine();      // reads user input from command line
            state = parseInput(input);  // converts the user input into a Case
        }

    }

    private String doAction(ShopState state) {

	    String response = null;

        switch (state) {
            case START:
                welcomeMessage();
                response = usageMessage();
                break;

            case USAGEMESSAGE:
                response = usageMessage();
                break;

            case EXIT:
                response = usageMessage();
                break;
            case USAGEMESSAGE:
                response = usageMessage();
                break;
            case EXIT:
                System.exit(0);
                break;
            case CART:
                ShoppingCart cart = ShoppingCart.getCart();
                break;
            case STORE:
                break;
            case LOGIN:
                login();
                break;
            case VIEWITEMS:
                break;
            case DONE:
                System.exit(0);
                break;
        }
        return response;
    }

    
    private ShopState login()
    {
        Scanner sc = new Scanner(new InputStreamReader(System.in));

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        User.UserType type = null;
        
        String email = null;
        String password = null;
        
        System.out.println("Please enter your username");
        email = sc.nextLine();
        
        System.out.println("Please enter your password");
        password = sc.nextLine();
        
        try {
			
                con = DatabaseConnector.getConnection();
                String stmtString = "SELECT * FROM Users WHERE Users.email=? and Users.password=?";
                stmt = con.prepareStatement(stmtString);
                stmt.setString(1, email);
                stmt.setString(2, password);
                rs = stmt.executeQuery();

                type = User.UserType.valueOf(rs.getString("type"));
                
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
        if (type.equals("Admin"))
            return ShopState.STORE;
        else if (type.equals("Employee"))
            return ShopState.STORE;
        else 
            return ShopState.STORE;
    }
    
    private ShopState parseInput(String input) {

	    ShopState state = null;
	    input = input.replaceAll("\\s+","");    // get rid of whitespace
        input = input.toUpperCase();    // make case-insensitive

	    try {
	        state = ShopState.valueOf(input);
        }
	    catch (IllegalArgumentException iae){
	        state = ShopState.USAGEMESSAGE;     // invalid input (no matching ShopState)
        }
	    catch (Exception e) {
	        e.printStackTrace();    // program actually crashed -- shouldn't happen
            System.exit(1);  // exit with error code flagged
        }
	    return state;
    }

    private String usageMessage() {
	    String msg = "These are your options:.... (REPLACE THIS): ";    // fill in with options
        return msg;
    }

    private void welcomeMessage() {
	    System.out.println("Welcome to the SoccerShop!");
    }
}
