package ba.ibu.edu.bemytech.rest.dto;

import ba.ibu.edu.bemytech.core.model.Product;

public class ProductRequestDTO {
    private String productName;
    private String description;
    private int quantityInStock;
    private double price;

    public ProductRequestDTO() {}

    public ProductRequestDTO(Product product) {
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.quantityInStock = product.getQuantityInStock();
        this.price = product.getPrice();
    }

    public Product toEntity() {
        Product product = new Product();
        product.setProductName(productName);
        product.setDescription(description);
        product.setQuantityInStock(quantityInStock);
        product.setPrice(price);
        return product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
