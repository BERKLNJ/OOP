import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private List<Order> orders;

    // Constructor
    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.orders = new ArrayList<>(); // Initialize the list to avoid NullPointerException
    }

    // --- Getter Methods ---
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    // --- Setter Methods ---
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // --- Utility Methods ---
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public String toString() {
        return "Restaurant{name='" + name + "', address='" + address + "', totalOrders=" + orders.size() + '}';
    }

    // Custom method for comparison
    public boolean hasSameName(Restaurant otherRestaurant) {
        return this.name.equalsIgnoreCase(otherRestaurant.name);
    }
}