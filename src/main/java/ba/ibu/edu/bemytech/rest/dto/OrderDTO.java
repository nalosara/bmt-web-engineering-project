package ba.ibu.edu.bemytech.rest.dto;

import ba.ibu.edu.bemytech.core.model.Order;
import ba.ibu.edu.bemytech.core.model.Product;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private String id;
    private List<Product> products;
    private String userId;
    private String address;
    private int quantity;
    private Date orderDate;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.products = order.getProducts();
        this.userId = order.getUserId();
        this.address = order.getAddress();
        this.quantity = order.getQuantity();
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
