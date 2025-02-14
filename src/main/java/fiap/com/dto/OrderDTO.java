package fiap.com.dto;

public class OrderDTO {
    private Long customerId;
    private Long productId;
    private int quantity;
    private String status;

    public OrderDTO() {}

    public OrderDTO(Long customerId, Long productId, int quantity, String status) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getCustomerId() { return customerId; }
    public Long getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }

    public void setStatus(String shipped) {
    }
}