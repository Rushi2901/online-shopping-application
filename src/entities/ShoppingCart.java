package entities;

import java.util.HashMap;
import java.util.Map;


public class ShoppingCart {
    private final Map < Product , Integer > items;     // reference is fixed but can chnage the value inside hashmap

    public ShoppingCart(){
        this.items = new HashMap<>();
    }

    public void addItem (Product product, int quantity){
        items.put(product,items.getOrDefault(product,0)+ quantity);


    }
    public void removeItem(Product product){
        items.remove(product);
    }
    
    public Map<Product,Integer> getItems(){
        return items;
    }
    public void clear (){
        items.clear();
    }



}
