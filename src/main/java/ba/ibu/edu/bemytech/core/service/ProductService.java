package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.core.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(@PathVariable int id) {
        return productRepository.findById(id);
    }
}
