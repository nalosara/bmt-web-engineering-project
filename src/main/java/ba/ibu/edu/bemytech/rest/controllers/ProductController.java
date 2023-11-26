package ba.ibu.edu.bemytech.rest.controllers;

import ba.ibu.edu.bemytech.core.service.ProductService;
import ba.ibu.edu.bemytech.rest.dto.ProductDTO;
import ba.ibu.edu.bemytech.rest.dto.ProductRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@SecurityRequirement(name = "JWT Security")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add-product")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductRequestDTO product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody ProductRequestDTO product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
