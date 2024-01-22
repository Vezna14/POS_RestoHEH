package adapter.persistence;

import be.heh.g2.Main;
import be.heh.g2.adapter.persistence.ProductRepository;
import be.heh.g2.adapter.persistence.ProductRowMapper;
import be.heh.g2.application.domain.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Main.class)
public class ProductRepositoryTest {


    @Autowired
    ProductRepository productRepository;
  /* @Test
   public void testdb(){
       List<Product> products= productRepository.findAllProducts();
       assertEquals(5,products.size());
       assertEquals("Produit A", products.get(0).getName());

   }
   */



    @Autowired
    private JdbcTemplate jdbcTemplate;

   /*
    @Test
    void testStoreProductInDB() {
        // Arrange
        Product product = new Product( 12 ,"TeststoreProduct", 29.99, new ArrayList<>(Arrays.asList("test1")), 50, "test.jpg");

        // Act
        productRepository.storeProductinDB(product);

        // Assert
        List<Product> products = productRepository.findAllProducts();

        // Adjust the expected size based on your initial data
       // assertEquals(4, products.size(), "The number of products should be 4 after adding a new product");

        // Assuming the new product is the last one, adjust the index based on your scenario
        assertEquals("TeststoreProduct", products.get(15).getName(), "The new product name should match");
        assertEquals(29.99, products.get(15).getPrice(), 0.001, "The new product price should match");
        assertEquals(Arrays.asList("test1"), products.get(15).getCategory(), "The new product category should match");
        assertEquals(50, products.get(15).getStock(), "The new product stock should match");
        assertEquals("test.jpg", products.get(15).getPhoto(), "The new product photo should match");
    }
    */

}



