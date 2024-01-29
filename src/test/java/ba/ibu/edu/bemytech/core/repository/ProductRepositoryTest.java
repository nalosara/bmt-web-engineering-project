package ba.ibu.edu.bemytech.core.repository;

import ba.ibu.edu.bemytech.core.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldReturnAllProducts() {
        List<Product> products = productRepository.findAll();

        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("BeMyStep", products.get(0).getName());
    }

    @Test
    public void shouldFindProductByProductName() {
        Optional<Product> product = productRepository.findByName("BeMyStep");
        Assertions.assertNotNull(product.orElse(null));
    }
}
