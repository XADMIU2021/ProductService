package productservice.productservice.service;

import org.springframework.stereotype.Service;
import productservice.productservice.domain.Product;
import productservice.productservice.domain.Stock;
import productservice.productservice.service.dtos.ProductDTO;
import productservice.productservice.service.dtos.ProductsDTO;
import productservice.productservice.service.dtos.StockDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductAdapterService {
    public ProductDTO getDTOFromProduct(Product product) {
        ProductDTO dto = new ProductDTO(product.getId(), product.getProductNumber(), product.getName(), product.getDescription(), product.getPrice());
        Stock stock = product.getStock();
        StockDTO stockDTO = new StockDTO(stock.getQuantity());
        dto.setStock(stockDTO);
        return dto;
    }

    public Product getProductFromDTO(ProductDTO dto) {
        Product product = new Product(dto.getProductNumber(), dto.getName(), dto.getDescription(), dto.getPrice());
        StockDTO stockDTO = dto.getStock();
        Stock stock = new Stock(stockDTO.getQuantity());
        product.setStock(stock);
        if (dto.getId() != null) product.setId(dto.getId());
        return product;
    }

    public ProductsDTO getDTOFromProducts(List<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        for (Product c: products) {
            productDTOS.add(this.getDTOFromProduct(c));
        }
        ProductsDTO dto = new ProductsDTO();
        dto.setProducts(productDTOS);
        return dto;
    }
}
