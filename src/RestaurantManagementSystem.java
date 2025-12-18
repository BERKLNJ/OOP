public class RestaurantManagementSystem {

    public static void main(String[] args) {

        System.out.println("--- 🍔 Initializing Restaurant Management System ---");

        // 1. Create MenuItem Instances
        MenuItem burger = new MenuItem(101, "Classic Burger", 12.99);
        MenuItem fries = new MenuItem(102, "Large Fries", 4.50);
        MenuItem soda = new MenuItem(103, "Cola", 2.00);
        MenuItem steak = new MenuItem(201, "Ribeye Steak", 35.00);

        // Demonstrate Getter/Setter for MenuItem
        soda.setPrice(2.50);
        soda.setAvailable(false); // Soda is currently out of stock

        System.out.println("\n--- MenuItem Details (After updates) ---");
        System.out.println(burger.toString());
        System.out.println("Soda Price: " + soda.getPrice() + ", Available: " + soda.isAvailable());

        // 2. Create Order Instances
        Order order1 = new Order(1);
        order1.addItem(burger, 1);
        order1.addItem(fries, 2); // 2 Large Fries
        // order1.addItem(soda, 1); // Cannot add soda, out of stock (in a real system, you'd check this)

        Order order2 = new Order(2);
        order2.addItem(steak, 1);
        order2.addItem(burger, 1);

        // Complete one order
        order1.setCompleted(true);

        System.out.println("\n--- Order Details ---");
        System.out.println("Order 1: " + order1.toString());
        System.out.println("Order 2: " + order2.toString());

        // 3. Create Restaurant Instances
        Restaurant fastEats = new Restaurant("Fast Eats", "123 Main St");
        Restaurant fineDine = new Restaurant("Fine Dine", "456 Oak Ave");

        // Add orders to the restaurant
        fastEats.addOrder(order1);
        fastEats.addOrder(order2);

        // Demonstrate Getter for Restaurant
        System.out.println("\n--- Restaurant Details ---");
        System.out.println(fastEats.getName() + " is located at " + fastEats.getAddress());
        System.out.println(fastEats.toString());
        System.out.println(fineDine.toString());

        // 4. Compare Multiple Objects

        // Comparing Orders
        System.out.println("\n--- Object Comparison ---");
        boolean orderComparison = order2.isMoreExpensiveThan(order1);
        System.out.println("Is Order 2 more expensive than Order 1? " + orderComparison + " (Order 1: $" + order1.getTotalAmount() + ", Order 2: $" + order2.getTotalAmount() + ")");

        // Comparing MenuItems
        boolean itemComparison = burger.isCheaperThan(steak);
        System.out.println("Is Burger cheaper than Ribeye Steak? " + itemComparison);

        // Comparing Restaurants
        Restaurant fastEatsDuplicate = new Restaurant("Fast Eats", "789 Pine St");
        boolean restaurantComparison = fastEats.hasSameName(fastEatsDuplicate);
        System.out.println("Do Fast Eats and Fast Eats Duplicate have the same name? " + restaurantComparison);

        // Standard Object Reference Comparison (comparing memory addresses)
        System.out.println("Are fastEats and fastEatsDuplicate the same object? " + (fastEats == fastEatsDuplicate));
    }
}