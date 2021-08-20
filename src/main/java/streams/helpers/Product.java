package streams.helpers;

import static streams.helpers.ProductCategory.GADGETS;

public class Product {
    private final String name;
    private final int price;
    private final ProductCategory category;

    public Product(String name, int price) {
        this(name, price, GADGETS);
    }

    public Product(String name, int price, ProductCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public ProductCategory getCategory() { return category; }

}
