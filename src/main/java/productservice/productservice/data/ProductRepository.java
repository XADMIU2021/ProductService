package productservice.productservice.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import productservice.productservice.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
