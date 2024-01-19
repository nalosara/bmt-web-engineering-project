package ba.ibu.edu.bemytech.core.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Cart {
    @Id
    private String id;
    private String username;
    private List<Product> product;
    private int quantity;

    public Cart(String id, List<Product> product, String username, int quantity) {
        this.id = id;
        this.product = product;
        this.username = username;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
