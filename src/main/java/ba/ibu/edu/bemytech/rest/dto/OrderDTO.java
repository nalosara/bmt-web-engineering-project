package ba.ibu.edu.bemytech.rest.dto;

import ba.ibu.edu.bemytech.core.model.Order;
import ba.ibu.edu.bemytech.core.model.Product;

import java.util.Date;

public class OrderDTO {
    private String id;
    private String userId;
    private String username;
    private Product product;
    private  int quantity;
    private String address;
    private Date orderDate;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.userId = order.getUserId();
        this.username = order.getUsername();
        this.product = order.getProduct();
        this.quantity = order.getQuantity();
        this.address = order.getAddress();
        this.orderDate = order.getOrderDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
