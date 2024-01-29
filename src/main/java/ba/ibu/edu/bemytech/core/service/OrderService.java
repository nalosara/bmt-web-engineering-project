package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.api.impl.mailsender.MailgunSender;
import ba.ibu.edu.bemytech.core.api.mailsender.MailSender;
import ba.ibu.edu.bemytech.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.bemytech.core.model.Contact;
import ba.ibu.edu.bemytech.core.model.Order;
import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.repository.OrderRepository;
import ba.ibu.edu.bemytech.rest.dto.OrderDTO;
import ba.ibu.edu.bemytech.rest.dto.OrderRequestDTO;
import ba.ibu.edu.bemytech.rest.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MailgunSender mailgunSender;

    public OrderService(OrderRepository orderRepository, MailgunSender mailgunSender) {
        this.orderRepository = orderRepository;
        this.mailgunSender = mailgunSender;
    }

    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(OrderDTO::new).collect(toList());
    }

    public OrderDTO getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("The order with the given ID does not exist!");
        }
        return new OrderDTO(order.get());
    }

    public List<OrderDTO> findByUsername(String username) {
        List<Order> orders = orderRepository.findByUsername(username);
        return orders.stream().map(OrderDTO::new).collect(toList());
    }

    public OrderDTO addOrder(OrderRequestDTO payload) {
        Order order = orderRepository.save(payload.toEntity());
        return new OrderDTO(order);
    }

    public List<OrderDTO> findByUserId(String userId) {
       List<Order> orders = orderRepository.findByUserId(userId);

        return orders.stream().map(OrderDTO::new).collect(toList());
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

    public boolean sendConfirmationEmail(User user, OrderDTO order) {
        try {
            String recipientEmail = user.getEmail();
            String subject = "Order Confirmation";
            String message = "Thank you for your order. Your order ID is: " + order.getId();
            System.out.println(message);
            mailgunSender.send(Collections.singletonList(user), message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
