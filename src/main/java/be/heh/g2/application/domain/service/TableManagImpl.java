package be.heh.g2.application.domain.service;

import be.heh.g2.application.domain.model.TableResto;
import be.heh.g2.application.port.in.TableRestoManagUseCase;
import be.heh.g2.application.port.out.ITableRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class TableManagImpl implements TableRestoManagUseCase {


    private  final ITableRepository tableRepository;

    public TableManagImpl(ITableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }


    @Override
    public List<TableResto> getAllTables(){
        return tableRepository.fetchAllTables();
    }

    @Override
    public String createTable(TableResto newTable){
        tableRepository.CreateTablesInRepository(newTable);
        return "success";
    }
    @Override
    public String deleteTable(int idTable){
        tableRepository.DeleteTablesInRepository(idTable);
        return "success";
    }
    @Override
    public String toggleStatutTable(int idTable) {
        try {
            tableRepository.toggleStatutTableInRepository(idTable);
            return "success";
        } catch (EmptyResultDataAccessException e) {
            // La table avec l'ID spécifié n'existe pas
            return "Table not found";
        }
    }


}
