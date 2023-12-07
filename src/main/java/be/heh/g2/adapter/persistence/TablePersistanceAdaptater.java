package be.heh.g2.adapter.persistence;

import be.heh.g2.application.domain.model.TableResto;
import be.heh.g2.application.port.out.ITableRepository;

import java.util.List;

public class TablePersistanceAdaptater implements ITableRepository {
    private final  TableRepository tableRepository;

    public TablePersistanceAdaptater(TableRepository  tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public List<TableResto> fetchAllTables(){
        return tableRepository.findAllTables();
    }
    //public Product fetchProductById()

    @Override
    public void CreateTablesInRepository(TableResto Table){
        tableRepository.createTable(Table);
    }
    @Override
    public void DeleteTablesInRepository(int idTable){
        tableRepository.deleteTable(idTable);
    }
    @Override
    public void toggleStatutTableInRepository(int idTable){
        tableRepository.toggleStatutTable(idTable);
    }

}
