package refactoring;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersWriter
{
    private final Orders ordersWrapper;

    public OrdersWriter(Orders ordersWrapper)
    {
        this.ordersWrapper = ordersWrapper;
    }

    public String getContents()
    {
        return ordersWrapper.asJson(getOrdersAsJson());
    }

    /**
     * Dezelfde methodes als hierboven, maar dan mbv de Stream API
     **/
    private String getOrdersAsJson()
    {
        return ordersWrapper.getOrders().stream()
                .map(order -> order.asJson(getProductsAsJson(order.getProducts())))
                .collect(Collectors.joining(", "));
    }

    private String getProductsAsJson(List<Product> products)
    {
        return products.stream().map(Product::asJson).collect(Collectors.joining(", "));
    }
}
