package be.heh.g2.adapter.web;

import be.heh.g2.application.common.ProductNotFoundException;
import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.port.in.InputProductValidator;
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

    //@GetMapping("/products")
    //public List<Product> products (){
      //  return productManagementUseCase.getAllProduct() ;
    //}
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productManagementUseCase.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try{
            Product product = productManagementUseCase.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e){
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
    }

    @PostMapping(value = "/product")
    public ResponseEntity<String> createProduct(@RequestBody InputProductValidator productToAdd) {
        // Assuming productManagementUseCase.createProduct returns a success indicator
        try {
            Product product= new Product(productToAdd.id(), productToAdd.name(), productToAdd.price(),productToAdd.category(),productToAdd.stock(), productToAdd.photo());
            productManagementUseCase.createProduct(product);
            return ResponseEntity.status(HttpStatus.OK).body("Product created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create product");
        }
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody InputProductValidator productToModify) {
        try {
            productManagementUseCase.modifyProduct(productToModify);
            return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update product");
        }
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            Product productToDelete = productManagementUseCase.getProductById(id);
            productManagementUseCase.deleteProduct(productToDelete);
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product");
        }
    }




}
