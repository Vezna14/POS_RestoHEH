package be.heh.g2.adapter.persistence;

import be.heh.g2.application.domain.model.TableResto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableRestoRowMapper implements RowMapper<TableResto> {

    @Override
    public TableResto mapRow(ResultSet rs, int rowNum) throws SQLException {


        int id = rs.getInt("id");
        String name = rs.getString("name");
        String status = rs.getString("status");
        int seats = rs.getInt("seats");
        boolean reserved = rs.getBoolean("reserved");


        return new TableResto(id, name, status, seats, reserved);
    }
}