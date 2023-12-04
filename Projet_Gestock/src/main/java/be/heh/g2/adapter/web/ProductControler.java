package be.heh.g2.adapter.web;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.port.in.ProductManagementUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ProductControler {

    private ProductManagementUseCase productManagementUseCase;

    public  ProductControler(ProductManagementUseCase productManagementUseCase){
        this.productManagementUseCase=productManagementUseCase;

    }

    @GetMapping("/products")
    public List<Product> products (){
        return productManagementUseCase.getAllProduct() ;
    }

    @GetMapping("/product/{id}")

    @PostMapping(value = "/product")
    public void POST(@RequestBody Product productToAdd){
        productManagementUseCase.createProduct(productToAdd);

    }

    /*
    public void createProduct(String name, double price, String category, int stock, String photo);

    public void modifyProductQuantity(long id, int new_quantity);

    public void deleteProduct(Product productToDelete);
    */
}
