public class Main {
    public static void main(String[] args) {
        // 1. Создаем несколько блюд (MenuItem)
        MenuItem burger = new MenuItem(1, "Бургер", 450.0);
        MenuItem fries = new MenuItem(2, "Картошка фри", 150.0);
        MenuItem cola = new MenuItem(3, "Кола", 100.0);

        // Используем сеттер, чтобы изменить цену (например, акция)
        cola.setPrice(80.0);

        // 2. Создаем заказы (Order)
        Order order1 = new Order(101);
        order1.addItem(burger, 2); // 2 бургера
        order1.addItem(cola, 1);   // 1 кола

        Order order2 = new Order(102);
        order2.addItem(fries, 3);  // 3 картошки

        // 3. Создаем ресторан
        Restaurant myRestaurant = new Restaurant("Best Burger", "ул. Ленина, 5");
        myRestaurant.addOrder(order1);
        myRestaurant.addOrder(order2);

        // 4. Вывод информации в консоль
        System.out.println("--- Информация о ресторане ---");
        System.out.println("Название: " + myRestaurant.getName());
        System.out.println("Заказов в системе: " + myRestaurant.getOrders().size());

        System.out.println("\n--- Детали заказов ---");
        System.out.println(order1.toString());
        System.out.println(order2.toString());

        // 5. Сравнение объектов (как требовалось в задании)
        System.out.println("\n--- Сравнение ---");

        // Сравним стоимость заказов
        if (order1.getTotalAmount() > order2.getTotalAmount()) {
            System.out.println("Заказ #101 дороже заказа #102");
        } else {
            System.out.println("Заказ #102 дороже или равен заказу #101");
        }

        // Сравним названия блюд
        boolean isSameFood = burger.getName().equals(fries.getName());
        System.out.println("Бургер и Фри — это одно и то же блюдо? " + isSameFood);
    }
}