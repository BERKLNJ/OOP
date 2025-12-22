import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant myRestaurant = new Restaurant("Best Burger", "ул. Ленина, 5");

        // 1. Наполняем меню (Полиморфизм: добавляем и еду, и напитки в один список)
        myRestaurant.addToMenu(new FoodItem("Бургер", 450.0, 600));
        myRestaurant.addToMenu(new FoodItem("Картошка фри", 150.0, 300));
        myRestaurant.addToMenu(new DrinkItem("Кола", 100.0, true));
        myRestaurant.addToMenu(new DrinkItem("Чай", 80.0, false));

        System.out.println("=== Система управления рестораном " + myRestaurant.getName() + " ===");

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Показать меню | 2. Сортировать по цене | 3. Поиск | 4. Фильтр цены | 5. Создать заказ | 0. Выход");
            System.out.print("> ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.println("--- Текущее меню ---");
                    // Демонстрация полиморфизма: вызываем getDescription() у разных объектов
                    for (BaseItem item : myRestaurant.filterByMaxPrice(10000)) {
                        System.out.println(item.getDescription());
                    }
                    break;
                case 2:
                    myRestaurant.sortByPrice();
                    System.out.println("Меню отсортировано по возрастанию цены.");
                    break;
                case 3:
                    System.out.print("Введите название для поиска: ");
                    String name = scanner.nextLine();
                    BaseItem found = myRestaurant.findItem(name);
                    System.out.println(found != null ? "Найдено: " + found.getDescription() : "Блюдо не найдено.");
                    break;
                case 4:
                    System.out.print("Введите максимальную цену: ");
                    double maxP = scanner.nextDouble();
                    myRestaurant.filterByMaxPrice(maxP).forEach(i -> System.out.println(i.getDescription()));
                    break;
                case 5:
                    // Пример создания старого объекта Order
                    Order order = new Order(201);
                    BaseItem itemForOrder = myRestaurant.findItem("Бургер");
                    if (itemForOrder != null) {
                        order.addItem(itemForOrder, 2);
                        myRestaurant.addOrder(order);
                        System.out.println("Заказ создан: " + order.toString());
                    }
                    break;
            }
        }
        System.out.println("Программа завершена.");
        scanner.close();
    }
}