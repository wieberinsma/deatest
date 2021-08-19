package refactoring;

import java.util.List;

public class Product {
    private final String code;
    private final int color;
    private final int size;
    private final double price;
    private final String currency;

    private static final List<String> availableSizes = List.of("XS", "S", "M", "L", "XL", "XXL");
    private static final List<String> availableColors = List.of("blue", "red", "yellow");

    public Product(String code, int color, int size, double price, String currency) {
        this.code = code;
        this.color = color;
        this.size = size;
        this.price = price;
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String asJson() {
        return "{" +
                    "\"code\": \"" + getCode() + "\", " +
                    (hasProperty(color) ? "\"color\": \"" + calculateColor(color) + "\", " : "") +
                    (hasProperty(size) ? "\"size\": \"" + calculateSize(size) + "\", " : "") +
                    "\"price\": " + getPrice() + ", " +
                    "\"currency\": \"" + getCurrency() + "\"" +
                "}";
    }

    private boolean hasProperty(int value) {
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
