import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderId;
    // Используем BaseItem вместо MenuItem, чтобы принимать и еду, и напитки
    private Map<BaseItem, Integer> items;
    private double totalAmount;
    private boolean isCompleted;

    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new HashMap<>();
        this.totalAmount = 0.0;
        this.isCompleted = false;
    }

    // --- Главное изменение: метод теперь принимает BaseItem ---
    public void addItem(BaseItem item, int quantity) {
        // Благодаря Полиморфизму, сюда можно передать и FoodItem, и DrinkItem
        this.items.put(item, this.items.getOrDefault(item, 0) + quantity);
        calculateTotal();
    }

    private void calculateTotal() {
        this.totalAmount = 0.0;
        for (Map.Entry<BaseItem, Integer> entry : items.entrySet()) {
            // Вызываем getPrice(), который определен в BaseItem
            this.totalAmount += entry.getKey().getPrice() * entry.getValue();
        }
    }

    // --- Геттеры и Сеттеры ---
    public int getOrderId() { return orderId; }
    public double getTotalAmount() { return totalAmount; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    @Override
    public String toString() {
        return "Order #" + orderId + " | Total: " + totalAmount + " KZT | Status: " + (isCompleted ? "Ready" : "In process");
    }
}