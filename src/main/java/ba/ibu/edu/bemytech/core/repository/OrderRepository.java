package ba.ibu.edu.bemytech.core.repository;

import ba.ibu.edu.bemytech.core.model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Repository
public class OrderRepository {
    private List<Order> orders;

    public OrderRepository() {
        this.orders = Arrays.asList(
                new Order(1, 1, 2, "Address 1", 1),
                new Order(2, 2, 1, "Address 2", 1)
        );
    }

    public List<Order> findAll() {
        return orders;
    }

    public Order findById(@PathVariable int id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst().orElse(null);
    }
}
