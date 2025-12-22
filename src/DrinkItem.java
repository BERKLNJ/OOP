public class DrinkItem extends BaseItem {
    private boolean isCold;

    public DrinkItem(String name, double price, boolean isCold) {
        super(name, price);
        this.isCold = isCold;
    }

    @Override
    public String getDescription() {
        String temp = isCold ? "Холодный" : "Горячий";
        return "[НАПИТОК] " + getName() + " | " + temp + " | Цена: " + getPrice();
    }
}