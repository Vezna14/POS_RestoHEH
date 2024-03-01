package be.heh.g2.application.port.out;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.domain.model.TableResto;

import java.util.List;

public interface ITableRepository {
    public List<TableResto> fetchAllTables();
    //public Product fetchProductById()

    public void CreateTablesInRepository(TableResto Table);
    public void DeleteTablesInRepository(int idTable);
    public void toggleStatutTableInRepository(int idTable);
}
