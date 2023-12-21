package ba.ibu.edu.bemytech.core.repository;

import ba.ibu.edu.bemytech.core.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void shouldReturnAllOrders() {
        List<Order> orders = orderRepository.findAll();

        Assertions.assertEquals(1, orders.size());
    }

    @Test
    public void shouldFindOrderByOrderAddress() {
        Optional<Order> order = orderRepository.findByAddress("Francuske revolucije bb");
        Assertions.assertNotNull(order.orElse(null));
    }

}
