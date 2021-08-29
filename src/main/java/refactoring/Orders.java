package refactoring;

import java.util.ArrayList;
import java.util.List;

public class Orders
{
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(Order order)
    {
        orders.add(order);
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public String asJson(String ordersAsJson)
    {
        return "{" +
                    "\"orders\": [" +
                        ordersAsJson +
                    "]" +
                "}";
    }
}
