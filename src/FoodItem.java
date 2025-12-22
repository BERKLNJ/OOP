public class FoodItem extends BaseItem {
    private int calories;

    public FoodItem(String name, double price, int calories) {
        super(name, price); // Вызов конструктора родителя
        this.calories = calories;
    }

    @Override
    public String getDescription() {
        return "[ЕДА] " + getName() + " | Калории: " + calories + " ккал | Цена: " + getPrice();
    }
}