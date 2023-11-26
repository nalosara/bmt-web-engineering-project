package ba.ibu.edu.bemytech.core.model;

import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {

    @Test
    void shouldCreateNewProduct() {
        Product product = new Product("id1", "New Product", "New Description", 10, 14.99);

        Assertions.assertEquals("New Product", product.getProductName());
    }

    @Test
    void shouldCompareTwoProducts() {
        Product product1 = new Product("someId", "Some product name", "Some product description", 20, 12.99);

        Product product2 = new Product("someId", "Some product name", "Some product description", 20, 12.99);

        AssertionsForInterfaceTypes
                .assertThat(product1)
                .usingRecursiveComparison()
                .isEqualTo(product2);
    }
}
