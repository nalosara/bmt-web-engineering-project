package ba.ibu.edu.bemytech.rest.dto;

import ba.ibu.edu.bemytech.core.model.Order;
import ba.ibu.edu.bemytech.core.model.Cart;

import java.util.Date;
import java.util.List;

public class OrderRequestDTO {
    private String userId;
    private String username;
    private List<Cart> products;
    private String address;

    public OrderRequestDTO() {}

    public OrderRequestDTO(Order order) {
        this.userId = order.getUserId();
        this.username = order.getUsername();
        this.products = order.getProducts();
        this.address = order.getAddress();
    }

    public Order toEntity() {
        Order order = new Order();
        order.setUserId(userId);
        order.setUsername(username);
        order.setProducts(products);
        order.setAddress(address);
        order.setOrderDate(new Date());
        return order;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Cart> getProducts() {
        return products;
    }

    public void setProducts(List<Cart> products) {
        this.products = products;
    }
}
