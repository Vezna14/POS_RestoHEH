package be.heh.g2.adapter.web;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.port.in.ProductManagementUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ProductController {

    private ProductManagementUseCase productManagementUseCase;

    public ProductController(ProductManagementUseCase productManagementUseCase){
        this.productManagementUseCase=productManagementUseCase;

    }

    @GetMapping("/products")
    public List<Product> products (){
        return productManagementUseCase.getAllProduct() ;
    }


    @PostMapping(value = "/product")
    public ResponseEntity<String> createProduct(@RequestBody Product productToAdd) {
        // Assuming productManagementUseCase.createProduct returns a success indicator
        try {
            productManagementUseCase.createProduct(productToAdd);
            return ResponseEntity.status(HttpStatus.OK).body("Product created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create product");
        }
    }



}
