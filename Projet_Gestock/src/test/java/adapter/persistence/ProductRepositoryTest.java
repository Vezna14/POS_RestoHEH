package adapter.persistence;

import be.heh.g2.Main;
import be.heh.g2.adapter.persistence.ProductRepository;
import be.heh.g2.application.domain.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = Main.class)
public class ProductRepositoryTest {


    @Autowired
    ProductRepository productRepository;
   @Test
   public void testdb(){
       List<Product> products= productRepository.findAllProducts();
       Assertions.assertEquals(3,products.size());
       Assertions.assertEquals("Produit A", products.get(0).getName());

   }




}
