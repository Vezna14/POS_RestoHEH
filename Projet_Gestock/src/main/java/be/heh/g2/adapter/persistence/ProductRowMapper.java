package be.heh.g2.adapter.persistence;

import be.heh.g2.application.domain.model.Product;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRowMapper implements RowMapper<Product> {


    //long id, String name, double price, ArrayList<String> category, int stock, String photo

    //Mapper le résultat de la requête en objets Product
    @Override
    public  Product mapRow(ResultSet rs,int rowNum) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("category"),  //cast en arrayList
                rs.getInt("stock"),
                rs.getString("photo")
        );
    }
}
