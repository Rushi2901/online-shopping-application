package entities;
import framework.ShopAcc;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private final ShopAcc account;
    private String address;
    private final ShoppingCart shoppingCart;    // one cart per customer implemented in shoppingcart class
    private final List<Order> order;


    public Customer(int userId ,String username,String email,String address,ShopAcc account){
        super(userId,username,email);
        this.address=address;
        this.shoppingCart =new ShoppingCart();
        this.order= new ArrayList<>();
        this.account=account;
    }

    public ShopAcc getAccount() {
        return account;
    }

    public String getAddress(){
        return address;
    }
    public ShoppingCart getShoppingCart(){
        return shoppingCart;
    }
    public List<Order> getOrder(){
        return order;
    }



}
