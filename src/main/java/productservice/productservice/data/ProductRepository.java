package productservice.productservice.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import productservice.productservice.domain.Product;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByProductNumber(String productNumber);

    List<Product> findByProductNumberIn(List<String> productNumbers);
}
