package productservice.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productservice.productservice.integration.KafkaSender;

@Service
public class CustomLoggerService {
    @Autowired
    private KafkaSender sender;
    public void log(String message) {
        sender.logToKafka(message);
    }
}
