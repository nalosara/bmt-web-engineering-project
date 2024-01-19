package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.core.model.Order;
import ba.ibu.edu.bemytech.core.model.Cart;
import ba.ibu.edu.bemytech.core.model.Product;
import ba.ibu.edu.bemytech.core.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@AutoConfigureMockMvc
@SpringBootTest
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;
/*
    @Test
    void shouldReturnOrderWhenCreated() {

        Product product1 = new Product("id1", "Name1", "Desc1", 7, 29.99);
        Product product2 = new Product("id2", "Name2", "Desc2", 3, 69.99);

        Cart orderedProduct1 = new Cart(product1, 2);
        Cart orderedProduct2 = new Cart(product2, 1);

        List<Cart> products = new ArrayList<>();
        products.add(orderedProduct1);
        products.add(orderedProduct2);

        Order order = new Order();
        order.setId("someId");
        order.setProducts(products);
        order.setUserId("someUserId");
        order.setAddress("Francuske revolucije bb");
        order.setOrderDate(new Date());

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderRepository.save(order);
        assertThat(order.getAddress()).isSameAs(savedOrder.getAddress());
        assertNotNull(savedOrder.getAddress());
    }*/
}
