package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.bemytech.core.model.Order;
import ba.ibu.edu.bemytech.core.model.Product;
import ba.ibu.edu.bemytech.core.repository.OrderRepository;
import ba.ibu.edu.bemytech.rest.dto.OrderDTO;
import ba.ibu.edu.bemytech.rest.dto.OrderRequestDTO;
import ba.ibu.edu.bemytech.rest.dto.ProductDTO;
import ba.ibu.edu.bemytech.rest.dto.ProductRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(OrderDTO::new).collect(toList());
    }

    public OrderDTO getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()) {
            throw new ResourceNotFoundException("The order with the given ID does not exist!");
        }
        return new OrderDTO(order.get());
    }

    public OrderDTO addOrder(OrderRequestDTO payload) {
        Order order = orderRepository.save(payload.toEntity());
        return new OrderDTO(order);
    }

    public OrderDTO updateOrder(String id, OrderRequestDTO payload) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("The order with the given ID does not exist.");
        }
        Order updatedOrder = payload.toEntity();
        updatedOrder.setId(order.get().getId());
        updatedOrder = orderRepository.save(updatedOrder);
        return new OrderDTO(updatedOrder);
    }

    public void deleteOrder(String id) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(orderRepository::delete);
    }
}
