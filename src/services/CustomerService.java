package services;
import entities.*;
import framework.ShopAcc;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();
    private final ProductService productService;
    private final OrderService orderService;


    // Inject Dependencies  in maion class
    public CustomerService(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    // add new customer
    public void addCustomer( Customer customer ){
        customers.add(customer);
    }

    // get all customers
    public List<Customer> getAllCustomers(){
        return new ArrayList<>(customers);

    }

    // placing and order and it wil be integrate with the ShopAcc i from frame work

    public void placeOrder(Customer customer){
        Map< Product , Integer> CartItems = customer.getShoppingCart().getItems();

        List <ProductQuantityPair> products = new ArrayList<>();
        float totalProductCharge=0;

        // validating Stock and calculating the charges
        for( Map.Entry<Product , Integer> entry : CartItems.entrySet()){
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.getStockQuantity() < quantity){
                System.out.println("Insufficient stock for: "+ product.getName());
                return;

            }

            totalProductCharge += (float) product.getPrice() * quantity;
            products.add( new ProductQuantityPair(product,quantity));


        }

        // acc specific charges for prime it is 0
        ShopAcc account = customer.getAccount();
        float totalChagre = account.bookProduct(totalProductCharge);


        System.out.println("Order placed successfully! Total: ₹" + totalChagre);

        // dedecut stock and create the order

        for (ProductQuantityPair pq : products){
            Product product = pq.getProduct();
            product.setStockQuantity(product.getStockQuantity()-pq.getQuantity());

        }

        Order order = new Order(customer,products);
        orderService.addOrder(order);
        customer.getOrder().add(order);
        customer.getShoppingCart().clear();

        System.out.println("Order placed successfully! Total: ₹" + totalChagre);

    }



}
