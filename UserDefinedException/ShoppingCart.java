package ExceptionHandling.UserDefinedException;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cartsItem;

    // Constructor to create a new shopping cart with an empty list of items.
    public ShoppingCart() {
        cartsItem = new ArrayList<>();
    }

    // Add the given quantity of the product to the cart, throws OutOfStockException if not available.
    public void addItem(Product orderProduct, int orderQuantity) throws OutOfStockException {
        if (!orderProduct.isAvailable(orderQuantity))
            throw new OutOfStockException(orderProduct.getProductName() + " is Out of Stock!");
        if (orderQuantity > 0) {
            cartsItem.add(orderProduct);
            orderProduct.reduceStock(orderQuantity);
            System.out.println(orderQuantity + " " + orderProduct.getProductName() + "(s) added to cart!");}
    }

    // Display the products in the cart or show "No item added" if the cart is empty.
    public void displayCart() {
        System.out.println("Shopping Bag products: ");
        if (cartsItem.isEmpty())
            System.out.println("No item added");
        else {for (Product product : cartsItem)
                System.out.println(product.getProductName());}
    }
}
