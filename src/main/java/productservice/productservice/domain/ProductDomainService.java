package productservice.productservice.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDomainService {
    public List<Product> recalculateQuantityInStock(OrderPlacedEvent event, List<Product> products) {
        List<OrderLineEvent> lines = event.getOrderLines();
        products.stream().forEach(product -> {
            OrderLineEvent l =  lines.stream()
                                .filter(line ->
                                        line.getProductNumber().equals(product.getProductNumber())).findAny().orElse(null
                                );
            Stock stock = product.getStock();
            stock.setQuantity(stock.getQuantity() - l.getQuantity());
            product.setStock(stock);
        });

        return products;
    }
}
