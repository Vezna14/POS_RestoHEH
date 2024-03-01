package be.heh.g2.adapter.persistence;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.domain.model.ProductOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.heh.g2.application.domain.model.Order;

public class OrderRowMapper implements RowMapper<Order> {


    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            int id = rs.getInt("id");
            int idTable = rs.getInt("idTable");
            String ordersString = rs.getString("productList");

            List<ProductOrder> productList = OrderRepository.deserializeProductList(ordersString);

            boolean isPaid = rs.getBoolean("isPaid");
            double totalPrice = rs.getDouble("totalPrice");
            String date = rs.getString("date");
            System.out.println("Serialrowmapper");

            return new Order(id, idTable, productList, isPaid, totalPrice, date);
        } catch (Exception e) {
            throw new SQLException("Error mapping Order", e);
        }
    }
    private  ArrayList<ProductOrder> mapProductOrders(String ordersString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductOrder[] productOrderArray = objectMapper.readValue(ordersString, ProductOrder[].class);
            return new ArrayList<>(Arrays.asList(productOrderArray));
        } catch (Exception e) {
            throw new RuntimeException("Error mapping Order products", e);
        }
    }
}
