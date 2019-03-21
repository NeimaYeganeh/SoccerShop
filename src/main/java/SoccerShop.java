
import java.io.InputStreamReader;
import java.util.Scanner;
import java.sql.*;

public class SoccerShop {
    public int sorttype = 1; /*ITEMIDASC*/
    public enum ShopState {
        START, USAGEMESSAGE, EXIT, STORE, CART, DONE, LOGIN, VIEWITEMS, ITEMDETAILS, SELECTTAGS, SELECTSORT, BACK, ITEMIDASC, ITEMIDDESC, ALPHAASC, ALPHADESC, PRICEASC, PRICEDESC
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
        welcomeMessage();
        response = startUsageMessage();
        System.out.print(response);
        input = sc.nextLine(); 
        state = parseInput(input);
        while (true) {

            response = doAction(state); // executes the action
            System.out.print(response); // displays next prompt
            input = sc.nextLine();      // reads user input from command line
            state = parseInput(input);  // converts the user input into a Case
        }

    }
    private String startUsageMessage() {
        String msg = "\nOptions:\n\t1) Store\n\t2) Cart\n\t3) Login\n\t4)Exit\nWhere do you want to go?\n(select by name)\n";    // fill in with options
        return msg;
    }
    private String doAction(ShopState state) {
	    Scanner sc = new Scanner(System.in);
	    String response = null;
	    String input = null;

        switch (state) {
            case STORE:
                System.out.print("Store\n");
                response = storeUsageMessage();
                System.out.print(response);
                input = sc.nextLine(); 
                state = parseInput(input);
                while(!startStore(state).equals("back")){
                    response = storeUsageMessage();
                    System.out.print(response);
                    input = sc.nextLine(); 
                    state = parseInput(input);
                }

                
                
                break;
            case CART:
                ShoppingCart cart = ShoppingCart.getCart();
                break;
            case LOGIN:
                login();
                break;
            case EXIT:
		exitUsageMessage();
                System.exit(0);
                break;
	    case USAGEMESSAGE:
            default:
                usageMessageStore();
                break;
	    
        }
	welcomeMessage();
	response = startUsageMessage();
        return response;
    }
    private String storeUsageMessage() {
        String msg = "\nOptions:\n\t1) View items\n\t2) Select tags\n\t3) Select sort\n\t4) Item Details\n\t5)Back\nWhere do you want to go?\n(select option by name)\n";    // fill in with options
        return msg;
    }
    private void tagUsageMessage() {

        System.out.println("\nOptions:\n\t1) (Enter TagID)\n\t2) Back\nSelect tage by tageID or 'back'.\n");
    }
    private void sortUsageMessage() {

        System.out.println("\nSort options:\n\t1) ItemID asc (default)\n\t2) ItemID desc\n\t3) Alpha asc\n\t4) Alpha desc\n\t5) Price asc\n\t6) Price desc\n\t7) Back\n Enter what to sort by or 'back'.\n");
    }
    private void usageMessageStore() {
        System.out.println("invalid option please choose from");
    }
    private void usageMessageSORT() {
        System.out.println("invalid option please choose from");
    }
    private String startStore(ShopState state) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        String response = null;
        String input = null;
        int op = 0;
        switch (state) {
            case VIEWITEMS:
                /* header with tags used*/
                /* prints items(id, name stock, price)*/
                /* response = viewitem (no action for while loop)*/
                response = "viewitem";
                break;

            case SELECTTAGS:
                /* prints tags and id, name, selected*/
                /* one at a time*/
                /* response = viewitem (no action for while loop)*/

                /*todo
                 * -print all tags (from tags table in db)*/
                System.out.println("\nSelect Tags");
                tagUsageMessage();
                input = sc.nextLine();
                state = parseInput(input);
                while(!state.equals("BACK")){
                    /* insert tags*/
                    try {
                        op = Integer.parseInt(input);
                        
                    } catch (NumberFormatException e) {
                        /* ussage message*/
                        usageMessageStore();

                    }
                    tagUsageMessage();
                    input = sc.nextLine(); 
                    state = parseInput(input);
                }
                response = "tags";
                break;
            case CLEARTAGS:
                /*clears all tags*/
                response = "clear";
                break;
            case SELECTSORT:
                System.out.println("\nSelect Sort");
                sortUsageMessage();
                boolean done = false;
                while (!done) {
                    input = sc.nextLine();
                    state = parseInput(input);
                    switch(state) {
                        case ITEMIDASC:
                            sorttype = 1;

                            break;
                        case ITEMIDDESC:
                            sorttype = 2;
                            break;
                        case ALPHAASC:
                            sorttype = 3;
                            break;
                        case ALPHADESC:
                            sorttype = 4;
                            break;
                        case PRICEASC:
                            sorttype = 5;
                            break;
                        case PRICEDESC:
                            sorttype = 6;
                            break;
                        case BACK:
                            done = true;
                            break;
                        case USAGEMESSAGE:
                        default:
                            usageMessageSORT();
                            break;
                    }
		    response =  "sort";
                }

                break;
            case ITEMDETAILS:

                response = "details";
                break;
            case BACK:
                response = "back";
                break;
            case USAGEMESSAGE:
            default:
                usageMessageStore();

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

    private String itemDetailsUsageMessage() {
        String msg = "\nOptions:\n\t1) (Enter item id)\n\t2) Back\nWhere do you want to go?\n(select by number)\n";    // fill in with options
        return msg;
    }
    private String itemDetailsUsageMessage() {
        String msg = "\nOptions:\n\t1) Add to cart\n\t2) Find another item\n\t3) Back\nWhere do you want to go?\n(select by number)\n";    // fill in with options
        return msg;
    }

    private void exitUsageMessage() {
        System.out.println("\nThank You choosing the SoccerShop!\nGoodbye\n");    // fill in with options
    }

    private void welcomeMessage() {
	    System.out.println("Welcome to the SoccerShop!");
    }
}
