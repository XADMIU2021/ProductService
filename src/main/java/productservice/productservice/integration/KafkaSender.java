package productservice.productservice.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void logToKafka(String message) {
        kafkaTemplate.send("webstore-log", message);
    }
}


