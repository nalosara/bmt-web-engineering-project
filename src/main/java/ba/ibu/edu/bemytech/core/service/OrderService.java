package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.core.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(@PathVariable int id) {
        return orderRepository.findById(id);
    }
}
