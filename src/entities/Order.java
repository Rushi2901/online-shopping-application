package entities;
import java.util.ArrayList ;
import java.util.List;



public class Order {
    private static int orderIdCounter=1;
    private final int orderId;
    private final Customer customer;
    private final List<ProductQuantityPair> products;
    private String status;
    public Order(Customer customer , List<ProductQuantityPair> products) {
        this.orderId=orderIdCounter++;
        this.customer=customer;
        this.products=new ArrayList<>(products);
        status="pending";
    }

    public int getOrderId() { return  orderId;}
    public String getStatus() { return status;}
    public  void setStatus (String status) { this.status=status;}
    public List<ProductQuantityPair> getProducts(){
        return products;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Order ID: %d , Status: %s\n",orderId,status));

    for (ProductQuantityPair pqp:products){
              sb.append(String.format("Product: %s , Quantity: %d\n",pqp.getProduct().getName(),pqp.getQuantity()));
          }
        return sb.toString();}




}
