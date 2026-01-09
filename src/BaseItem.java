import java.util.Objects;

public abstract class BaseItem {
    private String name; // Инкапсуляция: поля приватные
    private double price;

    public BaseItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    // Геттеры
    public String getName() { return name; }
    public double getPrice() { return price; }

    // Абстрактный метод (Полиморфизм: каждый потомок реализует его по-своему)
    public abstract String getDescription();

    // Переопределение toString()
    @Override
    public String toString() {
        return name + " - " + price + " KZT";
    }

    // Переопределение equals() и hashCode() для корректного сравнения объектов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseItem baseItem = (BaseItem) o;
        return Double.compare(baseItem.price, price) == 0 && Objects.equals(name, baseItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}