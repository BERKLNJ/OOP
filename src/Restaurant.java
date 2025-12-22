import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Restaurant {
    private String name;
    private String address;
    private List<Order> orders; // Список заказов (из старой версии)
    private List<BaseItem> menuPool; // Пул данных для фильтрации (новое требование)

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.orders = new ArrayList<>();
        this.menuPool = new ArrayList<>();
    }

    // --- Методы для работы с Меню (Поиск, Сортировка, Фильтрация) ---
    public void addToMenu(BaseItem item) {
        this.menuPool.add(item);
    }

    public void sortByPrice() {
        menuPool.sort(Comparator.comparingDouble(BaseItem::getPrice));
    }

    public BaseItem findItem(String name) {
        return menuPool.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<BaseItem> filterByMaxPrice(double maxPrice) {
        return menuPool.stream()
                .filter(item -> item.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // --- Старые методы для работы с заказами ---
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrders() { return orders; }
    public String getName() { return name; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return "Restaurant{name='" + name + "', menuSize=" + menuPool.size() + ", totalOrders=" + orders.size() + '}';
    }
}