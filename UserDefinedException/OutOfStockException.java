package ExceptionHandling.UserDefinedException;

// OutOfStockException class represents a custom exception for out-of-stock products.
public class OutOfStockException extends Exception {

    // Constructor to create a new OutOfStockException with the specified error message.
    public OutOfStockException(String message) {
        super(message);
    }
}
