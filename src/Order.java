import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderId;
    // Map to store MenuItem and its quantity
    private Map<MenuItem, Integer> items;
    private double totalAmount;
    private boolean isCompleted;

    // Constructor
    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new HashMap<>();
        this.totalAmount = 0.0;
        this.isCompleted = false;
    }

    // --- Getter Methods ---
    public int getOrderId() {
        return orderId;
    }

    public Map<MenuItem, Integer> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // --- Setter Methods ---
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // --- Utility Methods ---
    public void addItem(MenuItem item, int quantity) {
        this.items.put(item, this.items.getOrDefault(item, 0) + quantity);
        calculateTotal();
    }

    private void calculateTotal() {
        this.totalAmount = 0.0;
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            this.totalAmount += entry.getKey().getPrice() * entry.getValue();
        }
    }

    @Override
    public String toString() {
        return "Order{id=" + orderId + ", totalAmount=" + totalAmount + ", isCompleted=" + isCompleted + ", itemCount=" + items.size() + '}';
    }

    // Custom method for comparison
    public boolean isMoreExpensiveThan(Order otherOrder) {
        return this.totalAmount > otherOrder.totalAmount;
    }
}