package productservice.productservice.domain;

import java.util.List;

public class OrderPlacedEvent {
    private List<OrderLineEvent> orderLines;
    private String customerId;

    public OrderPlacedEvent() {
    }

    public OrderPlacedEvent(List<OrderLineEvent> orderLines, String customerId) {
        this.orderLines = orderLines;
        this.customerId = customerId;
    }

    public List<OrderLineEvent> getOrderLines() {
        return orderLines;
    }

    public String getCustomerId() {
        return customerId;
    }
}
