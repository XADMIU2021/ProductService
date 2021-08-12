package productservice.productservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import productservice.productservice.domain.OrderPlacedEvent;
import productservice.productservice.service.CustomLoggerService;
import productservice.productservice.service.ProductService;

@Service
public class KafkaReceiver {
    @Autowired
    private ProductService service;

    @Autowired
    private CustomLoggerService loggerService;

    @KafkaListener(topics = {"order-placed"})
    public void receiveAdd(@Payload String message) {
        System.out.println("Receiver received order placed message = " + message);
        loggerService.log("Received order place event : " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OrderPlacedEvent event = objectMapper.readValue(message, OrderPlacedEvent.class);
            service.handleOrderPlaced(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
