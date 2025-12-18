public class MenuItem {
    private int itemId;
    private String name;
    private double price;
    private boolean isAvailable;

    // Constructor
    public MenuItem(int itemId, String name, double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.isAvailable = true; // Default availability
    }

    // --- Getter Methods ---
    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // --- Setter Methods ---
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // --- Utility Methods ---
    @Override
    public String toString() {
        return "MenuItem{id=" + itemId + ", name='" + name + "', price=" + price + ", available=" + isAvailable + '}';
    }

    // Custom method for comparison
    public boolean isCheaperThan(MenuItem otherItem) {
        return this.price < otherItem.price;
    }
}