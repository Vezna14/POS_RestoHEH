package be.heh.g2.adapter.persistence;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.domain.model.TableResto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TableRepository {
    private final JdbcTemplate jdbc;

    public TableRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<TableResto> findAllTables() {
        String sql = "SELECT * FROM TableResto";
        return jdbc.query(sql, new TableRestoRowMapper());
    }
    public String deleteTable(int idTable){
        String sql = "DELETE FROM  TableResto WHERE id=?";
        jdbc.update(sql,idTable);
        return "success";

    }

    public String createTable(TableResto table) {
        try {
            System.out.println("Hello, repo start!");

            // Vérifier si la table existe déjà
            if (!isTableExists(table.getName())) {
                String sql = "INSERT INTO TableResto (name, status, seats, reserved) VALUES (?, ?, ?, ?)";
                jdbc.update(sql, table.getName(), table.getStatus(), table.getSeats(), table.isReserved());
                System.out.println("Table added successfully!");
                return "Table added successfully!";
            } else {
                System.out.println("Table already exists!");
                return "Table already exists!";
            }


        } catch (DataAccessException e) {
            // Gérer les exceptions liées à l'accès aux données (par exemple, erreur de requête SQL)
            System.err.println("Error creating table: " + e.getMessage());
            e.printStackTrace();
            return  e.getMessage();
        } catch (Exception e) {
            // Gérer d'autres exceptions
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return  e.getMessage();
        }
    }

    // Méthode pour vérifier si la table existe déjà
    private boolean isTableExists(String tableName) {
        String sql = "SELECT COUNT(*) FROM TableResto WHERE name = ?";
        int count = jdbc.queryForObject(sql, Integer.class, tableName);
        return count > 0;
    }

    public void toggleStatutTable(int idTable) {
        String selectSql = "SELECT status FROM TableResto WHERE id=?";
        String updateSql = "UPDATE TableResto SET status=? WHERE id=?";

        // Récupérer le statut actuel de la table
        String currentStatus = jdbc.queryForObject(selectSql, String.class, idTable);
        System.out.println("Table"+currentStatus);
        // Mettre à jour le statut de la table
        String newStatus = "available".equals(currentStatus) ? "occupied" : "available";
        jdbc.update(updateSql, newStatus, idTable);
        System.out.println("Table"+newStatus);
    }



}
