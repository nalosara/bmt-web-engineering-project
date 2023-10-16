package ba.ibu.edu.bemytech.core.repository;

import ba.ibu.edu.bemytech.core.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {
    public List<Product> products;

    public ProductRepository() {
        this.products = Arrays.asList(
                new Product(1, "BeMyStep", "Smart accessory for white cane that helps visually impaired", 149.99),
                new Product(2, "CAN Go Cane", "CAN Go is an ultra-comfortable, sturdy and sleekly designed cane hewn from aircraft-grade aluminum and sporting a soft, non-stick non-slip grip for enhanced safety.", 225.50)
        );
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(@PathVariable int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }
}
