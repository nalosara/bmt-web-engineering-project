package ba.ibu.edu.bemytech.rest.dto;

import ba.ibu.edu.bemytech.core.model.Order;
import ba.ibu.edu.bemytech.core.model.Product;

import java.util.Date;
import java.util.List;

public class OrderRequestDTO {
    private String userId;
    private String username;
    private Product product;
    private int quantity;
    private String address;

    public OrderRequestDTO() {}

    public OrderRequestDTO(Order order) {
        this.userId = order.getUserId();
        this.product = order.getProduct();
        this.username = order.getUsername();
        this.address = order.getAddress();
        this.quantity = order.getQuantity();
    }

    public Order toEntity() {
        Order order = new Order();
        order.setUserId(userId);
        order.setUsername(username);
        order.setQuantity(quantity);
        order.setProduct(product);
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
