package productservice.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productservice.productservice.data.ProductRepository;
import productservice.productservice.domain.OrderPlacedEvent;
import productservice.productservice.domain.Product;
import productservice.productservice.domain.ProductDomainService;
import productservice.productservice.service.dtos.ProductDTO;
import productservice.productservice.service.dtos.ProductsDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductAdapterService adapterService;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductDomainService domainService;

    @Autowired
    private CustomLoggerService loggerService;

    public ProductDTO save(ProductDTO dto) {
        Product product = adapterService.getProductFromDTO(dto);
        repository.save(product);
        dto.setId(product.getId());
        return dto;
    }

    public ProductDTO findById(String id) {
        Product product = repository.findById(id).orElse(null);
        if (product == null) return null;
        return adapterService.getDTOFromProduct(product);
    }

    public ProductDTO findByProductNumber(String productNumber) {
        List<Product> products = repository.findByProductNumber(productNumber);
        if (products == null || products.size() == 0 ) return null;

        Product product = products.get(0);
        return adapterService.getDTOFromProduct(product);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public ProductsDTO findAll() {
        List<Product> products = repository.findAll();
        return adapterService.getDTOFromProducts(products);
    }

    public void handleOrderPlaced(OrderPlacedEvent event) {
        List<String> productNumbers = event.getOrderLines().stream().map(line -> line.getProductNumber()).collect(Collectors.toList());
        List<Product> products = repository.findByProductNumberIn(productNumbers);

        products = domainService.recalculateQuantityInStock(event, products);
        repository.saveAll(products);
        loggerService.log("Stock quantity updated after placement for products length :  " + products.size());
    }
}
