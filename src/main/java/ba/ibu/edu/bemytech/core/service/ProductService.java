package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.bemytech.core.model.Product;
import ba.ibu.edu.bemytech.core.repository.ProductRepository;
import ba.ibu.edu.bemytech.rest.dto.ProductDTO;
import ba.ibu.edu.bemytech.rest.dto.ProductRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(ProductDTO::new).collect(toList());
    }

    public ProductDTO getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            throw new ResourceNotFoundException("The product with the given ID does not exist!");
        }
        return new ProductDTO(product.get());
    }

    public ProductDTO addProduct(ProductRequestDTO payload) {
        Product product = productRepository.save(payload.toEntity());
        return new ProductDTO(product);
    }

    public ProductDTO updateProduct(String id, ProductRequestDTO payload) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("The product with the given ID does not exist.");
        }
        Product updatedProduct = payload.toEntity();
        updatedProduct.setId(product.get().getId());
        updatedProduct = productRepository.save(updatedProduct);
        return new ProductDTO(updatedProduct);
    }

    public void deleteProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(productRepository::delete);
    }
}
