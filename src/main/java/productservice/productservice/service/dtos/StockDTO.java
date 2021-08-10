package productservice.productservice.service.dtos;

public class StockDTO {
    private int quantity;

    public StockDTO() {
    }

    public StockDTO(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
