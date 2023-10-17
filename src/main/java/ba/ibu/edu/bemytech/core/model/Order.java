package ba.ibu.edu.bemytech.core.model;

public class Order {
    private int id;
    private int productId;
    private int userId;
    private String address;
    private int quantity;

    public Order(int id, int productId, int userId, String address, int quantity) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.address = address;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
