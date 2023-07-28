package ExceptionHandling.UserDefinedException;

public class Product {
    private String productName;
    private int productStockQuantity;

    // Constructor to initialize the product with its name and stock quantity.
    public Product(String productName, int productStockQuantity) {
        this.productName = productName;
        this.productStockQuantity = productStockQuantity;
    }

    // Get the product name.
    public String getProductName() { return productName;}

    // Get the current stock quantity of the product.
    public int getProductStockQuantity() { return productStockQuantity; }

    // Check if the desired quantity is available in stock.
    public boolean isAvailable(int quantity) { return quantity <= productStockQuantity; }

    // Reduce the stock quantity by the specified amount.
    public void reduceStock(int quantity) { productStockQuantity -= quantity;}
}
