package be.heh.g2.application.port.in;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.domain.model.TableResto;

import java.util.List;

public interface TableRestoManagUseCase {

    public List<TableResto> getAllTables();


    public String createTable(TableResto newTable);
    public String deleteTable(int idTable);
    public String toggleStatutTable(int idTable);
}
