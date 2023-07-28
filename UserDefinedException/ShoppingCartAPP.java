package ExceptionHandling.UserDefinedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingCartAPP {
    public static void main(String[] args) {

        // Create a map to store products and their corresponding quantities obtained from user input.
        Map<Product, Integer> productQuantities = new HashMap<>();

        productQuantities.put(new Product("Denim Jacket", 10), getUserQuantityInput("Jacket"));
        productQuantities.put(new Product("Laptop", 5), getUserQuantityInput("Laptops"));
        productQuantities.put(new Product("Smartphone", 2), getUserQuantityInput("Smartphones"));
        productQuantities.put(new Product("PUMA", 7), getUserQuantityInput("Shoes"));

        // Create a new shopping cart.
        ShoppingCart cart = new ShoppingCart();

        // Add products to the cart and handle OutOfStockException if occurred.
        for (Map.Entry<Product, Integer> entry : productQuantities.entrySet()) {
            try { cart.addItem(entry.getKey(), entry.getValue()); }
            catch (OutOfStockException e) { System.out.println("Error: " + e.getMessage());}
        }

        // Display the contents of the cart.
        cart.displayCart();
    }

    // Helper method to get the user input for product quantity.
    private static int getUserQuantityInput(String product) {
        Scanner scanner = new Scanner(System.in);
        int quantity = 0;
        do {
            System.out.print("Enter the quantity of " + product + " you want to add to the cart: ");
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                try { quantity = Integer.parseInt(input);}
                catch (NumberFormatException e) { System.out.println("Invalid input. Please enter a valid number.");}
            }
        } while (quantity < 0);
        return quantity;
    }
}
