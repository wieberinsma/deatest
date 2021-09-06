package refactoring;

import java.util.ArrayList;
import java.util.List;

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

    private String getOrdersAsJson()
    {
        List<String> ordersAsJson = new ArrayList<>();

        for (Order order : ordersWrapper.getOrders())
        {
            String productsAsJson = getProductsAsJson(order.getProducts());
            ordersAsJson.add(order.asJson(productsAsJson));
        }

        return String.join(", ", ordersAsJson);
    }

    private String getProductsAsJson(List<Product> products)
    {
        List<String> productsAsJson = new ArrayList<>();

        for (Product product : products)
        {
            productsAsJson.add(product.asJson());
        }

        return String.join(", ", productsAsJson);
    }

    /**
     * Dezelfde methodes als hierboven, maar dan mbv de Stream API
     **/
//    private String getOrdersAsJson()
//    {
//        return ordersWrapper.getOrders().stream().map(order -> order.asJson(getProductsAsJson(order.getProducts())))
//                .collect(Collectors.joining(", "));
//    }
//
//    private String getProductsAsJson(List<Product> products)
//    {
//        return products.stream().map(Product::asJson).collect(Collectors.joining(", "));
//    }
}
