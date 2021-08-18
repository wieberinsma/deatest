package refactoring;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int id;

    private final List<Product> products = new ArrayList<>();

    public Order(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return id;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}
