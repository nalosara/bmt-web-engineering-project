package ba.ibu.edu.bemytech.core.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class OrderTest {
/*
    @Test
    public void shouldCreateNewOrder() {
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

        Assertions.assertEquals("someId", order.getId());
        Assertions.assertEquals(products, order.getProducts());
        Assertions.assertEquals("someUserId", order.getUserId());
        Assertions.assertEquals("Francuske revolucije bb", order.getAddress());
        Assertions.assertNotNull(order.getOrderDate());
    }*/
}
