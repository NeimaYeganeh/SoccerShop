import java.util.ArrayList;

public class ShoppingCart {

    private static ShoppingCart thisCart;
    private static Order order;
    private static ArrayList<Item> items;

    private ShoppingCart() {
        thisCart = this;
        items = new ArrayList<>();
    }

    // singleton constructor
    public static ShoppingCart getCart() {
        if (thisCart == null) {
            thisCart = new ShoppingCart();
        }
        return thisCart;
    }

    // Attempts to add Item to Cart; Fails if not enough stock in DB
    public boolean addItem(Item item) {

        boolean inCart = false;
        int cartQuantity = item.getQuantity();

        // increment quantity of item in cart
        for (Item i : items) {
            if (i.equals(item)) {
                inCart = true;
                cartQuantity += i.getQuantity();
                if (cartQuantity > DatabaseConnector.getStock(item))
                    return false;
                i.setQuantity(cartQuantity);
            }
        }

        if (!inCart) {
            if (cartQuantity > DatabaseConnector.getStock(item))
                return false;
            items.add(item);
        }
        return true;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void buyShoppingCart() {

        order = new Order();
        double totalPrice = 0;

        for (Item i : new ArrayList<>(items)) {
            DatabaseConnector.purchaseItem(i);
            totalPrice += i.getPrice()*i.getQuantity();
        }

        // send finalized Order to database
        order.setPrice(totalPrice);
        order.setStatus(Order.Status.Awaiting);
        DatabaseConnector.storeOrder(order);
    }
}
