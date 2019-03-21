
//import DatabaseConnector;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.sql.*;

public class SoccerShop {
    public int[] sorttype = {1, 0, 0, 0, 0, 0}; /*ITEMIDASC*/
    public enum ShopState {
        START, USAGEMESSAGE, EXIT, STORE, CART, DONE, LOGIN, VIEWITEMS, ADMIN, EMPLOYEE, 
        ITEMDETAILS, SELECTTAGS, SELECTSORT, BACK, ITEMIDASC, ITEMIDDESC, ALPHAASC, ALPHADESC,
        PRICEASC, PRICEDESC , CLEARTAGS
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
            case START:
                this.type = null;
                break;
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

                welcomeMessage();
                response = startUsageMessage();
                break;
            case CART:
                ShoppingCart cart = ShoppingCart.getCart();
                break;
            case LOGIN:
                login();
                
                response = startUsageMessage();
                break;
            
            case EXIT:
                  exitUsageMessage();
                System.exit(0);
                break;
       case USAGEMESSAGE:
            default:
                //response = usageMessageAction();
                break;
       
        }
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
            case LOGIN:
                response = login().toString();
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
                break;
            case CLEARTAGS:
                /*clears all tags*/
                response = "clear";
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
       /* private ShopState employee()
        {
            ShopState response;
        
            case SELECTSORT:
                System.out.println("\nSelect Sort");
                sortUsageMessage();
                int done = 0;
                while (!done) {
                    input = sc.nextLine();
                    state = parseInput(input);
                    switch(state) {
                        case ITEMIDASC:
                            sorttype[0] = 1;
                            sorttype[1] = 0;
                            sorttype[2] = 0;
                            sorttype[3] = 0;
                            sorttype[4] = 0;
                            sorttype[5] = 0;

                            break;
                        case ITEMIDDESC:
                            sorttype[0] = 0;
                            sorttype[1] = 1;
                            sorttype[2] = 0;
                            sorttype[3] = 0;
                            sorttype[4] = 0;
                            sorttype[5] = 0;
                            break;
                        case ALPHAASC:
                            sorttype[0] = 0;
                            sorttype[1] = 0;
                            sorttype[2] = 1;
                            sorttype[3] = 0;
                            sorttype[4] = 0;
                            sorttype[5] = 0;
                            break;
                        case ALPHADESC:
                            sorttype[0] = 0;
                            sorttype[1] = 0;
                            sorttype[2] = 0;
                            sorttype[3] = 1;
                            sorttype[4] = 0;
                            sorttype[5] = 0;
                            break;
                        case PRICEASC:
                            sorttype[0] = 0;
                            sorttype[1] = 0;
                            sorttype[2] = 0;
                            sorttype[3] = 0;
                            sorttype[4] = 1;
                            sorttype[5] = 0;
                            break;
                        case PRICEDESC:
                            sorttype[0] = 0;
                            sorttype[1] = 0;
                            sorttype[2] = 0;
                            sorttype[3] = 0;
                            sorttype[4] = 0;
                            sorttype[5] = 1;
                            break;
                        case BACK:
                            done = 1;
                            break;
                        case USAGEMESSAGE:
                            break;
                        default:
                            response = usageMessageSORT();
                            break;
                    }
                }

                break;
            case ITEMDETAILS:

                
                break;
            case BACK:
                response = "back"
                break;
            case USAGEMESSAGE:
            default:
                usageMessageStore();

                break;

            
        }
        return response;
    }*/
    
    private ShopState employee()
    {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        System.out.println("Please enter: 1 to view orders, 2 to add items,"
                + " 3 to change personal info, or 4 to go back");
        
        switch (sc.nextInt()){
            case 1:
                Order.Status status = null;
                                 
            System.out.println("Filter by Order Status?\t Enter 0 to view all orders,"
                    + " 1 to filter by Current, 2 for Awaiting, 3 for Processing,\n "
                    + "\t\t\t 4 for Failed, 5 for Shipped, 6 for Completed ");

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
                Order.viewOrders(DatabaseConnector.getConnection(), status);
                break;
            case 2:
                
                System.out.println("Enter item number");
                int i_num = sc.nextInt();
                System.out.println("Enter stock amount to be updated");
                
                Item.updateItemStock(DatabaseConnector.getConnection(),i_num, sc.nextInt());
                break;

            case 3:
                System.out.println("Change Personal Info");
                break;
            case 4:
                return ShopState.START;
            default:
                System.out.println("Error: Invalid option selected");
                break;
        }
        employee();
        return ShopState.START;

    }
    //If user is admin, they can also view employees, and create and delete admin/employees
      private ShopState admin()
      {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        System.out.println("Please enter: 1 to view orders, 2 to add items,"
                + " 3 to change personal info, 4 to view employees,\n "
                + " \t      5 to create an employee/admin, 6 to delete an employee/admin,"
                + " or 7 to go back");
        
        switch (sc.nextInt()){
            case 1:
                Order.Status status = null;
                                 
            System.out.println("Filter by Order Status?\t Enter 0 to view all orders,"
                    + " 1 to filter by Current, 2 for Awaiting, 3 for Processing,\n "
                    + "\t\t\t 4 for Failed, 5 for Shipped, 6 for Completed ");

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
                Order.viewOrders(DatabaseConnector.getConnection(), status);
                break;
            case 2:
                
                System.out.println("Enter item number");
                int i_num = sc.nextInt();
                System.out.println("Enter stock amount to be updated");
                
                Item.updateItemStock(DatabaseConnector.getConnection(),i_num, sc.nextInt());
                break;

            case 3:
                System.out.println("Change Personal Info");
                
                break;
            case 4:
                User.getUsers(DatabaseConnector.getConnection());
                break;
            case 5:
                
                sc.nextLine();
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
                break;
            case 6:
                System.out.println("Enter user email");
                User.deleteUser(DatabaseConnector.getConnection(), sc.nextLine());
                break;
            case 7:
                return ShopState.START;
            default:
                System.out.println("Error: Invalid option selected");
                break;
        }
        admin();
        return ShopState.START;
    }
    
    private ShopState login()
    {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int resp = 0;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String type = null;
         
        String email = null;
        String password = null;
        
        System.out.println("\tPlease enter 1 to Login, or 2 to go Back");
        if( (resp = sc.nextInt()) == 2)
            return ShopState.START;
        else if ( resp != 1)
        {
            System.out.println("Error: Invalid option selected.");
            return ShopState.LOGIN;
        }
        //proceed to log in
        sc.nextLine();
        System.out.println("\tPlease enter your email");
        email = sc.nextLine();
        
        System.out.println("\tPlease enter your password");
        password = sc.nextLine();
        
        try { 
                con = DatabaseConnector.getConnection();
                String stmtString = "SELECT * FROM Users WHERE Users.email=? and Users.password=?;";
                
                stmt = con.prepareStatement(stmtString);
                stmt.setString(1, email);
                stmt.setString(2, password);
                rs = stmt.executeQuery();
                while (rs.next())
                {                
                    System.out.println("\nSucessfully logged in as " + rs.getString("email"));
                    type = rs.getString("type");
                    System.out.println("User type: " + type + "\n");
                }                

        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                try {
                        stmt.close();
                        con.close();
                        if ( type.equals("admin"))
                        {
                            admin();
                            return ShopState.LOGIN;
                        }
                        else if ( type.equals("employee"))
                        {
                            employee();
                            return ShopState.LOGIN;
                        }
                        else
                        {
                            return ShopState.STORE;
                        }
                         
                } catch (SQLException e) {
                        e.printStackTrace();
                } 
        }
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


    private void exitUsageMessage() {
        System.out.println("\nThank You choosing the SoccerShop!\nGoodbye\n");    // fill in with options
    }

    private void welcomeMessage() {
       System.out.println("Welcome to the SoccerShop!");
    }
}
