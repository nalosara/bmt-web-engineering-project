package ba.ibu.edu.bemytech.core.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class OrderTest {
    @Test
    public void shouldCreateNewOrder() {
        Product product = new Product("id1", "Name1", "Desc1", 7, 29.99);

        Order order = new Order();
        order.setId("someId");
        order.setProduct(product);
        order.setUserId("someUserId");
        order.setAddress("Francuske revolucije bb");
        order.setOrderDate(new Date());

        Assertions.assertEquals("someId", order.getId());
        Assertions.assertEquals(product, order.getProduct());
        Assertions.assertEquals("someUserId", order.getUserId());
        Assertions.assertEquals("Francuske revolucije bb", order.getAddress());
        Assertions.assertNotNull(order.getOrderDate());
    }
}
