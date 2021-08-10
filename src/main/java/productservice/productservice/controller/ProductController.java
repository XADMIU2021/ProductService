package productservice.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import productservice.productservice.domain.Product;
import productservice.productservice.service.ProductService;
import productservice.productservice.service.dtos.ProductDTO;
import productservice.productservice.service.dtos.ProductsDTO;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<ProductsDTO> getAllProducts() {
        ProductsDTO dto = productService.findAll();
        return new ResponseEntity<ProductsDTO>(dto, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO dto) {
        productService.save(dto);
        return new ResponseEntity<ProductDTO>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO dto, @PathVariable String id) {
        ProductDTO product = productService.findById(id);
        if (product == null) return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);

        dto.setId(product.getId());
        productService.save(dto);
        return new ResponseEntity<ProductDTO>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id) {
        ProductDTO dto = productService.findById(id);
        if (dto == null) return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<ProductDTO>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProdcut(@PathVariable String id) {
        ProductDTO product = productService.findById(id);
        if (product == null) return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);

        productService.deleteById(id);
        return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);
    }
}
