package ba.ibu.edu.bemytech.rest.dto;

import ba.ibu.edu.bemytech.core.model.Order;
import ba.ibu.edu.bemytech.core.model.Product;

import java.util.Date;
import java.util.List;

public class OrderRequestDTO {
    private String userId;
    private List<Product> products;
    private String address;
    private int quantity;

    public OrderRequestDTO() {}

    public OrderRequestDTO(Order order) {
        this.userId = order.getUserId();
        this.products = order.getProducts();
        this.address = order.getAddress();
        this.quantity = order.getQuantity();
    }

    public Order toEntity() {
        Order order = new Order();
        order.setUserId(userId);
        order.setProducts(products);
        order.setAddress(address);
        order.setQuantity(quantity);
        order.setOrderDate(new Date());
        return order;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
