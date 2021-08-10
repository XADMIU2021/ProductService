package productservice.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productservice.productservice.data.ProductRepository;
import productservice.productservice.domain.Product;
import productservice.productservice.service.dtos.ProductDTO;
import productservice.productservice.service.dtos.ProductsDTO;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductAdapterService adapterService;

    @Autowired
    private ProductRepository repository;

    public ProductDTO save(ProductDTO dto) {
        Product product = adapterService.getProductFromDTO(dto);
        repository.save(product);
        dto.setId(product.getId());
        return dto;
    }

    public ProductDTO findById(String id) {
        Product product = repository.findById(id).orElse(null);
        return adapterService.getDTOFromProduct(product);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public ProductsDTO findAll() {
        List<Product> products = repository.findAll();
        return adapterService.getDTOFromProducts(products);
    }
}
