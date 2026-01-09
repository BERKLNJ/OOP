public class RestaurantManagementSystem {

    public static void main(String[] args) {

        System.out.println("--- 🍔 Initializing Restaurant Management System ---");

        // 1. Создаем объекты FoodItem и DrinkItem (вместо старого MenuItem)
        // Теперь мы используем Наследование и Полиморфизм
        BaseItem burger = new FoodItem("Classic Burger", 12.99, 600);
        BaseItem fries = new FoodItem("Large Fries", 4.50, 400);
        BaseItem soda = new DrinkItem("Cola", 2.50, true);
        BaseItem steak = new FoodItem("Ribeye Steak", 35.00, 800);

        // Демонстрация инкапсуляции: меняем цену через сеттер в BaseItem
        soda.setPrice(2.75);

        System.out.println("\n--- Обновленные детали блюд ---");
        System.out.println(burger.getDescription()); // Полиморфизм в действии
        System.out.println(soda.getDescription());

        // 2. Работа с заказами
        Order order1 = new Order(1);
        order1.addItem(burger, 1); // Теперь принимает BaseItem без ошибок
        order1.addItem(fries, 2);

        Order order2 = new Order(2);
        order2.addItem(steak, 1);
        order2.addItem(burger, 1);

        // Отмечаем заказ как выполненный
        order1.setCompleted(true);

        System.out.println("\n--- Детали заказов ---");
        System.out.println(order1.toString());
        System.out.println(order2.toString());

        // 3. Работа с рестораном (Data Pool)
        Restaurant fastEats = new Restaurant("Fast Eats", "123 Main St");

        // Добавляем блюда в Пул Данных (menuPool) для поиска и фильтрации
        fastEats.addToMenu(burger);
        fastEats.addToMenu(fries);
        fastEats.addToMenu(soda);
        fastEats.addToMenu(steak);

        fastEats.addOrder(order1);
        fastEats.addOrder(order2);

        // 4. Демонстрация поиска и фильтрации
        System.out.println("\n--- Поиск и Фильтрация ---");

        // Сортировка по цене
        fastEats.sortByPrice();
        System.out.println("Меню после сортировки:");
        fastEats.getMenu().forEach(item -> System.out.println(item.toString()));

        // Поиск
        BaseItem found = fastEats.findItem("Cola");
        System.out.println("\nРезультат поиска 'Cola': " + (found != null ? found.getDescription() : "Не найдено"));

        // Фильтрация (блюда дешевле 10$)
        System.out.println("\nБлюда дешевле $10.00:");
        fastEats.filterByMaxPrice(10.00).forEach(item -> System.out.println(item.getDescription()));

        // 5. Сравнение объектов (используя переопределенный equals)
        System.out.println("\n--- Сравнение объектов ---");
        BaseItem anotherBurger = new FoodItem("Classic Burger", 12.99, 600);
        System.out.println("Это тот же самый бургер? " + burger.equals(anotherBurger));
    }
}