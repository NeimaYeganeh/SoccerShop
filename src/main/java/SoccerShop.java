import java.io.InputStreamReader;
import java.util.Scanner;

public class SoccerShop {

    public enum ShopState {Start, Exit};

    private ShopState state;

	public SoccerShop() {
	    this.state = ShopState.Start;
    }

    // Main UI for the SoccerShop
    public void launchCLI() {

        Scanner sc = new Scanner(new InputStreamReader(System.in));
        String input = null;
        String response = null;

        while (state != ShopState.Exit) {

            response = doAction(state);
            System.out.print(response);
            input = sc.nextLine();
            state = parseInput(input);
        }

    }

    public String doAction(ShopState state) {

	    String response = null;

        switch (state) {
            case Start:
                response = "yo what up: ";
        }

        return response;
    }

    public ShopState parseInput(String input) {
	    ShopState state = null;

	    try {
	        state = ShopState.valueOf(input);
        }
	    catch (IllegalArgumentException iae){
	        iae.printStackTrace();
        }
	    catch (Exception e) {
	        e.printStackTrace();
        }
	    return state;
    }
	
}
