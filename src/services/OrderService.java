package services;
import entities.Order;
import java.util.ArrayList;
import java.util.List;


public class OrderService {
    private final List<Order> orders = new ArrayList<>();


    //add new order to the  system
    public void addOrder(Order order){
        orders.add(order);
    }

    // get all the orders
    public List<Order> getOrder(){
        return new ArrayList<>(orders);
    }

    //find order by uingb orde rid
    public Order getOrderById (int orderId){
        return orders.stream()
                .filter(order -> order.getOrderId()== orderId)
                .findFirst()
                .orElse(null);
    }

}
