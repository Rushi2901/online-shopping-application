package services;
import entities.Product;
import java.util.ArrayList;
import java.util.List;



public class ProductService {
    private final List<Product> products = new ArrayList<>();

    // Add a new product
    public void addProduct(Product product){
        products.add(product);
    }
    // Remove a product by Id
    public void removeProduct( int ProductId){
        products.removeIf( product -> product.getProductId()==ProductId);

    }

    // get all product
    public List<Product> getProducts(){
        return  new ArrayList<>(products);
    }

    // find by  a product ID
     public Product getProductById(int productId){
        return  products.stream()
                .filter(proID -> proID.getProductId() == productId)
                .findFirst()
                .orElse(null);


     }


}
