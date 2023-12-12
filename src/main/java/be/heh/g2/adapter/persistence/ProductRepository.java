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
    public int getProductStock(int productId) {
        String sql = "SELECT stock FROM Product WHERE id = ?";
        return jdbc.queryForObject(sql, Integer.class, productId);
    }

    public void updateProductStock(int productId, int newStock) {
        String sql = "UPDATE Product SET stock = ? WHERE id = ?";
        jdbc.update(sql, newStock, productId);
    }

}










