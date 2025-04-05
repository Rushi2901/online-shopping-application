package services;
import entities.admin;
import entities.Order;
import java.util.ArrayList;
import java.util.List;
import entities.Product;
import entities.ProductQuantityPair;


public class AdminService {
    private final List<admin> admins = new ArrayList<>();
    private final OrderService orderService;

    public AdminService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void addAdmin(admin admin){
    admins.add(admin);
    }

    public void updateOrderStatus(int orderId,String newstatus) {
        Order order = orderService.getOrderById(orderId);

        if (order == null) {
            System.out.println(" Order ID: " + orderId + " not found");
            return;
        }
        String oldStatus = order.getStatus();
        order.setStatus(newstatus);

        // restore stock if order is cancelled
        if (newstatus.equals("cancelled") && oldStatus.equals("cancelled")) {
            restoreStock(order);
        }
        // Deduct stock if order is reactivated (e.g., from Cancelled to Pending)
        if (oldStatus.equals("Cancelled") && !newstatus.equals("Cancelled")) {
            deductStock(order);
        }

    }

        private void restoreStock(Order order) {
            for (ProductQuantityPair pq : order.getProducts()) {
                Product product = pq.getProduct();
                product.setStockQuantity(product.getStockQuantity() + pq.getQuantity());
            }
        }

        private void deductStock(Order order){
            for (ProductQuantityPair pq: order.getProducts()){
                Product product = pq.getProduct();
                product.setStockQuantity(product.getStockQuantity()-pq.getQuantity());

            }
        }

        public List<admin> getAllAdmin(){
        return new ArrayList<>(admins);
        }

    }


