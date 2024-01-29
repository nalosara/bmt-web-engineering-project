package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.core.model.Product;
import ba.ibu.edu.bemytech.core.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Test
    void shouldReturnProductWhenCreated() {
        Product product = new Product("someId", "Some name", "Some description", 11, 11.99);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productRepository.save(product);
        assertThat(product.getName()).isSameAs(savedProduct.getName());
        assertNotNull(savedProduct.getName());
    }
}
