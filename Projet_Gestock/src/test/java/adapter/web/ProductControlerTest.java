package adapter.web;

import be.heh.g2.adapter.web.ProductControler;
import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.port.in.ProductManagementUseCase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@WebMvcTest(controllers = ProductControler.class)
public class ProductControlerTest {
    private ProductManagementUseCase productManagementUseCase;


public ArrayList<Product> ShouldReturnproductList201()
    {
        ArrayList<Product> productsList = new ArrayList<Product>();
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("boisson");
        categories.add("chaud");

        Product produit1 = new Product(1, "pizza", 12.5, categories, 80, "urlphoto");
        Product produit2 = new Product(2, "cafe", 3, categories, 12, "urlphoto");
        productsList.add(produit1);
        productsList.add(produit2);
        return productsList;

    }
}
