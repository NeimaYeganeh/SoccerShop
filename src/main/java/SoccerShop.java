import java.io.InputStreamReader;
import java.util.Scanner;

public class SoccerShop {

    public enum ShopState {Start, UsageError, Exit};

    private ShopState state;

	public SoccerShop() {
	    this.state = ShopState.Start;
    }

    // Main UI for the SoccerShop
    public void launchCLI() {

        Scanner sc = new Scanner(new InputStreamReader(System.in));
        String input = null;
        String response = null;

        /*
        *   main control flow of program
        *   quits program when "Exit" command entered
        */
        while (true) {

            response = doAction(state); // executes the action, returns next prompt
            System.out.print(response); // displays prompt
            input = sc.nextLine();      // reads user input from command line
            state = parseInput(input);  // converts the user input into a Case
        }

    }

    private String doAction(ShopState state) {

	    String response = null;

        switch (state) {
            case Start:
                response = "yo what up: ";

            case UsageError:
                response = usageMessage();

            case Exit:
                System.exit(0);
        }
        return response;
    }

    private ShopState parseInput(String input) {
	    ShopState state = null;

	    try {
	        return ShopState.valueOf(input);
        }
	    catch (IllegalArgumentException iae){
	        state = ShopState.UsageError;
        }
	    catch (Exception e) {
	        e.printStackTrace();
        }
	    return state;
    }

    private String usageMessage() {
	    String msg = "";    // fill in with options
        return msg;
    }
	
}
