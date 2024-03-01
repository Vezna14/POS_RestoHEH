package be.heh.g2.adapter.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(value ="name", defaultValue = "World")String name){
        return String.format("Hello %s!",name);
    }

    /*@GetMapping("/products")
    public List<Product> products(){
        ProductManagementImpl prodmng = new ProductManagementImpl();
        List<Product> productsList = prodmng.getAllProduct();

        return productsList;
    }
    /*
     */

}
