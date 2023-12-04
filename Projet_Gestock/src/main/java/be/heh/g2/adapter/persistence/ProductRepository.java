package be.heh.g2.adapter.persistence;

import be.heh.g2.application.domain.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// DAO
@Repository
public class ProductRepository {
    private final JdbcTemplate jdbc;

    public ProductRepository(JdbcTemplate jbdc) {
        this.jdbc = jbdc;
    }

    public void storeProductinDB(Product product) {
        String sql = "INSERT INTO Product VALUES (NULL,?,?,?,?,?,?)";
        jdbc.update(sql,
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getPhoto());
    }

    public List<Product> findAllProducts(){
        String sql = "SELECT * FROM Product";
        //return null;
        return jdbc.query(sql, new ProductRowMapper());
    }

}










