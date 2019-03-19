import java.util.Scanner;

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
                System.exit(0);
                break;

            case CART:
                break;

            case STORE:
                break;

            case LOGIN:
                break;

            case VIEWITEMS:
                break;

            case DONE:
                break;

        }
        return response;
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
	    System.out.println("Welcome to SoccerShop!");
    }
	
}
