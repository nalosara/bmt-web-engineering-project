package ba.ibu.edu.bemytech.rest.dto;

import ba.ibu.edu.bemytech.core.model.Order;
import ba.ibu.edu.bemytech.core.model.OrderedProduct;

import java.util.Date;
import java.util.List;

public class OrderRequestDTO {
    private String userId;
    private List<OrderedProduct> products;
    private String address;

    public OrderRequestDTO() {}

    public OrderRequestDTO(Order order) {
        this.userId = order.getUserId();
        this.products = order.getProducts();
        this.address = order.getAddress();
    }

    public Order toEntity() {
        Order order = new Order();
        order.setUserId(userId);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProduct> products) {
        this.products = products;
    }
}
