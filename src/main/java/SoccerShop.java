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

            switch (state) {
                case Start:

                case Exit:
                    return;
            }

            System.out.println(response);
            input = sc.nextLine();
        }

    }
	
}
