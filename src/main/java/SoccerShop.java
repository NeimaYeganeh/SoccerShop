
import java.io.InputStreamReader;
import java.util.Scanner;
import java.sql.*;

public class SoccerShop {

    public enum ShopState {
        START, USAGEMESSAGE, EXIT, STORE, CART, DONE, LOGIN, VIEWITEMS, ADMIN, EMPLOYEE
    }

    private ShopState state;
    private User.UserType type;
    
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
            System.out.println(response); // displays next prompt
            input = sc.nextLine();      // reads user input from command line
            state = parseInput(input);  // converts the user input into a Case
        }

    }

    private String doAction(ShopState state) {

	    String response = null;

        switch (state) {
            case START:
                this.type = null;
                welcomeMessage();
                response = usageMessage();
                break;
            case USAGEMESSAGE:
                response = usageMessage();
                break;

            case EXIT:
                response = usageMessage();
                break;

            case CART:
                break;
            case STORE:
                break;
            case LOGIN:
                response = login().toString();
                break;
            case VIEWITEMS:
                break;
            case DONE:
                System.exit(0);
                break;
        }
        return response;
    }

    /*--------------------------------------------------------------------------
    If user is employee, options are
        1. View orders
        2. Add items/stock
        3. Change personal info
    If user is admin, they can also view employees, and create and delete admin/employees
    --------------------------------------------------------------------------*/
        private ShopState employee()
    {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        System.out.println("Please enter 1 to view orders, 2 to add items,"
                + " 3 to change personal info, or 4 to go back");
        
        switch (sc.nextInt()){
            case 1:
                Order.Status status = null;
                                 
            System.out.println("Filter by Order Status? Enter 0 to view all orders,"
                    + " 1 to filter by Current, 2 for Awaiting, 3 for Processing, "
                    + "4 for Failed, 5 for Shipped, 6 for Completed ");

                switch (sc.nextInt()){
                case 0:
                    break;
                case 1:
                    status = Order.Status.Current;
                    break;
                case 2:
                    status = Order.Status.Awaiting;
                    break;
                case 3:
                    status = Order.Status.Processing;
                    break;
                case 4:
                    status = Order.Status.Failed;
                    break;
                case 5:
                    status = Order.Status.Shipped;
                    break;
                case 6:
                    status = Order.Status.Completed;
                    break;
                default:
                    break;
                }
                Order.viewOrders(status);
                break;
            case 2:
                
                System.out.println("Enter item number");
                int i_num = sc.nextInt();
                System.out.println("Enter stock amount to be updated");
                
                Item.updateItemStock(DatabaseConnector.getConnection(),i_num, sc.nextInt());
                return ShopState.EMPLOYEE;

            case 3:
                System.out.println("Change Personal Info");
                
                break;
            case 4:
                return ShopState.START;
            default:
                System.out.println("Error: Invalid option selected");
                return ShopState.EMPLOYEE;
        }
        return ShopState.START;

    }
    If user is admin, they can also view employees, and create and delete admin/employees
        private ShopState admin()
    {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        System.out.println("Please enter 1 to view orders, 2 to add items,"
                + " 3 to change personal info, 4 to view employees, "
                + " 5 to create an employee/admin, 6 to delete an employee/admin,"
                + " or 7 to go back");
        
        switch (sc.nextInt()){
            case 1:
                Order.Status status = null;
                                 
            System.out.println("Filter by Order Status? Enter 0 to view all orders,"
                    + " 1 to filter by Current, 2 for Awaiting, 3 for Processing, "
                    + "4 for Failed, 5 for Shipped, 6 for Completed ");

                switch (sc.nextInt()){
                case 0:
                    break;
                case 1:
                    status = Order.Status.Current;
                    break;
                case 2:
                    status = Order.Status.Awaiting;
                    break;
                case 3:
                    status = Order.Status.Processing;
                    break;
                case 4:
                    status = Order.Status.Failed;
                    break;
                case 5:
                    status = Order.Status.Shipped;
                    break;
                case 6:
                    status = Order.Status.Completed;
                    break;
                default:
                    break;
                }
                Order.viewOrders(status);
                break;
            case 2:
                
                System.out.println("Enter item number");
                int i_num = sc.nextInt();
                System.out.println("Enter stock amount to be updated");
                
                Item.updateItemStock(DatabaseConnector.getConnection(),i_num, sc.nextInt());
                return ShopState.ADMIN;

            case 3:
                System.out.println("Change Personal Info");
                
                break;
            case 4:
                User.getUsers(DatabaseConnector.getConnection());
                return ShopState.ADMIN;
            case 5:
                
                
                System.out.println("Enter user email");
                String email = sc.nextLine();
                
                System.out.println("Enter password");
                String password = sc.nextLine();
                
                System.out.println("Enter user last name");
                String lastName = sc.nextLine();

                System.out.println("Enter user first name");
                String firstName = sc.nextLine();

                System.out.println("Enter user type (admin or employee)");
                User.UserType type = User.UserType.valueOf(sc.nextLine());


                User.insertUser(DatabaseConnector.getConnection(), email, password, lastName,firstName,type);
                return ShopState.ADMIN;
            case 6:
                System.out.println("Enter user email");
                User.deleteUser(DatabaseConnector.getConnection(), sc.nextLine());
                return ShopState.ADMIN;
            default:
                System.out.println("Error: Invalid option selected");
                return ShopState.ADMIN;
        }
        return ShopState.START;

    }
    
    
    
    
    
    
    
    
    private ShopState login()
    {
        
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int resp = 0;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
                
        String email = null;
        String password = null;
        
        System.out.println("Please enter 1 to Login, or 2 to go Back");
        if( (resp = sc.nextInt()) == 2)
            return ShopState.START;
        else if ( resp != 1)
        {
            System.out.println("Error: Invalid option selected.");
            return ShopState.LOGIN;
        }
        //proceed to log in
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

                this.type = User.UserType.valueOf(rs.getString("type"));
                
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
        return ShopState.ADMIN;
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
