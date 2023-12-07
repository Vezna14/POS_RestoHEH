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
        String sql = "INSERT INTO Product (name, category, price, stock, photo) VALUES (?,?,?,?,?)";
        jdbc.update(sql,
                product.getName(),
                String.join(",", product.getCategory()), // Joining categories as a comma-separated string
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










