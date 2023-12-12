package be.heh.g2.adapter.web;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.domain.model.TableResto;
import be.heh.g2.application.port.in.TableRestoManagUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

public class TableRestoController {
    private final TableRestoManagUseCase TableRestoManageUseCase;


    public TableRestoController(TableRestoManagUseCase tableRestoManageUseCase) {
        this.TableRestoManageUseCase = tableRestoManageUseCase;
    }
    @GetMapping("/tableRestos")
    public List<TableResto> tableRestos (){
        return TableRestoManageUseCase.getAllTables() ;
    }

    @DeleteMapping("/deleteTable/{id}")
    public ResponseEntity<String>deleteTable(@PathVariable int id){
        String result = TableRestoManageUseCase.deleteTable(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @PostMapping(value = "/tableRestos")
    public ResponseEntity<String> createTable(@RequestBody TableResto newTable) {
        try {
            String result = TableRestoManageUseCase.createTable(newTable);


            // Le résultat pourrait être un message ou un code, en fonction de la logique de votre cas d'utilisation.
            if ("Table already exists!".equals(result)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(result);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create table");
        }
    }

    @PutMapping("/toggleStatus/{id}")
    public ResponseEntity<String> toggleStatutTable(@PathVariable int id) {
        try {
            // Récupérer la table par son ID
            String result = TableRestoManageUseCase.toggleStatutTable(id);

            if (result == "") {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Table not found");
            }

            return ResponseEntity.status(HttpStatus.OK).body("Table status toggled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to toggle table status");
        }
    }
}
