package be.heh.g2.adapter.persistence;

import be.heh.g2.application.domain.model.Order;
import be.heh.g2.application.domain.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            int id = rs.getInt("id");
            int idTable = rs.getInt("idTable");
            String ordersString = rs.getString("productList");

            ArrayList<Product> productList = mapOrders(ordersString);

            boolean isPaid = rs.getBoolean("isPaid");
            double totalPrice = rs.getDouble("totalPrice");
            String date = rs.getString("date");

            return new Order(id, idTable, productList, isPaid, totalPrice, date);
        } catch (Exception e) {
            throw new SQLException("Error mapping Order", e);
        }
    }

    private ArrayList<Product> mapOrders(String ordersString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Product[] productArray = objectMapper.readValue(ordersString, Product[].class);
            return new ArrayList<>(Arrays.asList(productArray));
        } catch (Exception e) {
            throw new RuntimeException("Error mapping Order products", e);
        }
    }
}
