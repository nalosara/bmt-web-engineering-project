package ba.ibu.edu.bemytech.rest.controllers;

import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.service.OrderService;
import ba.ibu.edu.bemytech.core.service.UserService;
import ba.ibu.edu.bemytech.rest.dto.OrderDTO;
import ba.ibu.edu.bemytech.rest.dto.OrderRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@SecurityRequirement(name = "JWT Security")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {

        this.orderService = orderService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add-order")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderDTO order = orderService.addOrder(orderRequestDTO);
        if (order != null) {
            User user = userService.findById(order.getUserId());
            boolean emailSent = orderService.sendConfirmationEmail(user, order);
            if (!emailSent) {
                System.out.println("The email was not sent.");
            }
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/order-by-id/{userId}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<List<OrderDTO>> findByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(orderService.findByUserId(userId));
    }
    @RequestMapping(method = RequestMethod.GET, path = "/order-by-username/{username}")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    public ResponseEntity<List<OrderDTO>> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(orderService.findByUsername(username));
    }
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable String id, @RequestBody OrderRequestDTO order) {
        return ResponseEntity.ok(orderService.updateOrder(id, order));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
