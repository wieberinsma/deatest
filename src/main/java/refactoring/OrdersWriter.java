package refactoring;

import java.util.ArrayList;
import java.util.List;

public class OrdersWriter {
    private final Orders orders;

    private static final List<String> availableSizes = List.of("XS", "S", "M", "L", "XL", "XXL");

    private static final List<String> availableColors = List.of("blue", "red", "yellow");

    public OrdersWriter(Orders orders) {
        this.orders = orders;
    }

    public String getContents() {
        return "{\"orders\": ["  + getOrderArray(orders) + "]}";
    }

    private String getOrderArray(Orders orders) {
        List<String> ordersAsJson = new ArrayList<>();

        for (Order order : orders.getOrders()) {
            ordersAsJson.add(
                    "{" +
                        "\"id\": " + order.getOrderId() + ", " +
                        "\"products\": [" + getProductArray(order.getProducts()) + "]" +
                    "}");
        }

        return String.join(", ", ordersAsJson);
    }

    private String getProductArray(List<Product> products) {
        List<String> productsAsJson = new ArrayList<>();

        for (Product product : products) {
            int color = product.getColor();
            int size = product.getSize();

            productsAsJson.add(
                    "{" +
                        "\"code\": \"" + product.getCode() + "\", " +
                        (hasData(color) ? "\"color\": \"" + calculateColor(color) + "\", " : "") +
                        (hasData(size) ? "\"size\": \"" + calculateSize(size) + "\", " : "") +
                        "\"price\": " + product.getPrice() + ", " +
                        "\"currency\": \"" + product.getCurrency() + "\"" +
                    "}");
        }

        return String.join(", ", productsAsJson);
    }

    private boolean hasData(int value) {
        return value != -1;
    }

    private String calculateColor(int color) {
        return validColor(color) ? availableColors.get(color - 1) : "no color";
    }

    private boolean validColor(int color) {
        return color > 0 && color <= availableColors.size();
    }

    private String calculateSize(int size) {
        return validSize(size) ? availableSizes.get(size - 1) : "Invalid Size";
    }

    private boolean validSize(int size) {
        return size > 0 && size <= availableSizes.size();
    }
}
