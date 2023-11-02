package ba.ibu.edu.bemytech.rest.dto;

import ba.ibu.edu.bemytech.core.model.Order;
import ba.ibu.edu.bemytech.core.model.OrderedProduct;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private String id;
    private List<OrderedProduct> products;
    private String userId;
    private String address;
    private Date orderDate;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.products = order.getProducts();
        this.userId = order.getUserId();
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

    public List<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProduct> products) {
        this.products = products;
    }
}
