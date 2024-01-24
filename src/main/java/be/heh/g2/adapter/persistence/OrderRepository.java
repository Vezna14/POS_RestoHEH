package be.heh.g2.adapter.persistence;

import be.heh.g2.application.domain.model.Order;
import be.heh.g2.application.domain.model.ProductOrder;
import be.heh.g2.application.domain.model.TableResto;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.security.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private final JdbcTemplate jdbc;
    private  ProductRepository productRepository;
    public OrderRepository(JdbcTemplate jbdc ,ProductRepository productRepository) {
        this.jdbc = jbdc;
        this.productRepository =productRepository;
    }

    public void saveOrder(Order order) {

        System.out.println("ProductList before serialization: " + order.getProductList());

        // Assurez-vous que productList n'est pas null
        if (order.getProductList() == null) {
            order.setProductList(new ArrayList<>());
        }

        // Sérialisez ProductList en chaîne JSON après vous être assuré qu'elle n'est pas null
        String productListJson = serializeProductList(order.getProductList());
        System.out.println("Serialized ProductList JSON: " + productListJson);
        // Utilisez Instant.now() pour obtenir l'instant actuel
        Instant instant = Instant.now();
        java.sql.Date date = new java.sql.Date(instant.toEpochMilli());




        String sql = "INSERT INTO orders (idTable, productList, isPaid, totalPrice, date) VALUES (?, ?, ?, ?,?)";

        jdbc.update(sql, order.getIdTable(), productListJson, order.isPaid(), order.getTotalPrice(),date);

        // Mettez à jour le stock de produit pour chaque ProductOrder dans la liste
        for (ProductOrder productOrder : order.getProductList()) {
            int productId = productOrder.getId();
            int quantity = productOrder.getQuantity();
            int currentStock = productRepository.getProductStock(productId);
            int newStock = currentStock - quantity;

            // Mettez à jour le stock de produit
            productRepository.updateProductStock(productId, newStock);
        }
    }



    private String serializeProductList(List<ProductOrder> productList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("SerializedProductListObjectmapper: ");
            return objectMapper.writeValueAsString(productList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing productList to JSON", e);
        }
    }

    // Deserialize JSON String to List<ProductOrder>
    public static List<ProductOrder> deserializeProductList(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ProductOrder> productList = objectMapper.readValue(json, new TypeReference<List<ProductOrder>>() {});
            // Ajoutez des logs pour afficher les résultats
            System.out.println("JSON désérialisé avec succès : " + productList);
            return productList;
        } catch (IOException e) {
            // Ajoutez des logs pour afficher l'erreur
            System.err.println("Erreur de désérialisation du JSON : " + json);
            e.printStackTrace();
            // Gérez l'exception ou relancez-la selon vos besoins
            throw new RuntimeException("Erreur de désérialisation du produit", e);
        }
    }

    public void payOrder(int idTable) {
        String sql = "UPDATE orders SET isPaid = true WHERE idTable = ? AND isPaid = false";
        jdbc.update(sql, idTable);
    }

    public Order showOrder(int idTable) {
        String sql = "SELECT * FROM orders WHERE idTable = ? ORDER BY date DESC LIMIT 1";
        return jdbc.queryForObject(sql, new OrderRowMapper(), idTable);
    }


}


