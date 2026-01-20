import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant myRestaurant = new Restaurant("Best Burger", "ул. Ленина, 5");
        System.setProperty("file.encoding", "UTF-8");
        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 1. Инициализируем менеджер базы данных (ОБЯЗАТЕЛЬНО)
        DatabaseManager dbManager = new DatabaseManager();

        // Наполняем начальное меню
        myRestaurant.addToMenu(new FoodItem("Burger", 450.0, 600));
        myRestaurant.addToMenu(new FoodItem("Fries", 150.0, 300));
        myRestaurant.addToMenu(new DrinkItem("Cola", 100.0, true));
        myRestaurant.addToMenu(new DrinkItem("Tea", 80.0, false));

        System.out.println("=== System of restaurant management " + myRestaurant.getName() + " ===");

        while (true) {
            System.out.println("\nВыберите действие:");
            // Обновили меню, добавив пункты 6 и 7
            System.out.println("1. Menu | 2. Sorting | 3. Search | 4. Filter | 5. Order | 6. Add New | 7. Show DB | 0. Exit");
            System.out.print("> ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.println("--- Current Menu ---");
                    for (BaseItem item : myRestaurant.getMenu()) {
                        System.out.println(item.getDescription());
                    }
                    break;
                case 2:
                    myRestaurant.sortByPrice();
                    System.out.println(" Sorted Menu ");
                    break;
                case 3:
                    System.out.print(" Name ");
                    String searchName = scanner.nextLine();
                    BaseItem found = myRestaurant.findItem(searchName);
                    System.out.println(found != null ? "Found: " + found.getDescription() : "Not found.");
                    break;
                case 4:
                    System.out.print("Max price: ");
                    double maxP = scanner.nextDouble();
                    myRestaurant.filterByMaxPrice(maxP).forEach(i -> System.out.println(i.getDescription()));
                    break;
                case 5:
                    Order order = new Order(201);
                    System.out.print("What ordering? (название): ");
                    String orderItem = scanner.nextLine();
                    BaseItem itemForOrder = myRestaurant.findItem(orderItem);
                    if (itemForOrder != null) {
                        order.addItem(itemForOrder, 1);
                        myRestaurant.addOrder(order);
                        System.out.println("Order created: " + order.toString());
                    } else {
                        System.out.println("Not found.");
                    }
                    break;
                case 6:
                    // Исправлено: используем scanner вместо sc и добавляем логику типов
                    System.out.print("Give name: ");
                    String dbName = scanner.nextLine();
                    System.out.print("Give price: ");
                    double dbPrice = scanner.nextDouble();
                    System.out.print("Type(1 - Food, 2 - Drink) : ");
                    int type = scanner.nextInt();

                    BaseItem newItem;
                    if (type == 1) {
                        System.out.print("Calories: ");
                        newItem = new FoodItem(dbName, dbPrice, scanner.nextInt());
                    } else {
                        System.out.print("Cold? (true/false): ");
                        newItem = new DrinkItem(dbName, dbPrice, scanner.nextBoolean());
                    }

                    // Сохраняем в базу через dbManager
                    dbManager.saveItem(newItem);
                    // Также добавим в текущий список ресторана, чтобы поиск работал сразу
                    myRestaurant.addToMenu(newItem);
                    System.out.println("Successful");
                    break;
                case 7:
                    System.out.println("--- List of dishes ---");
                    dbManager.readMenu();
                    break;
                default:
                    System.out.println("INVALID CHOICE.");
                    break;
            }
        }
        System.out.println("Shutting down...");
        scanner.close();
    }
}