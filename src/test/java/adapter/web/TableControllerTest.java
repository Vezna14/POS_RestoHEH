package adapter.web;

import be.heh.g2.adapter.web.TableRestoController;
import be.heh.g2.application.domain.model.TableResto;
import be.heh.g2.application.port.in.TableRestoManagUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TableControllerTest {

    @Mock
    private TableRestoManagUseCase tableRestoManageUseCase;

    @InjectMocks
    private TableRestoController tableRestoController;

    @Test
    void testGetAllTables() {
        // Mocking the behavior of the TableRestoManageUseCase
        when(tableRestoManageUseCase.getAllTables()).thenReturn(Arrays.asList(
                new TableResto(1, "Table 1","occupé",12,false),
                new TableResto(2, "Table 2","occupé",13,false)
        ));

        // Calling the controller method
        List<TableResto> result = tableRestoController.tableRestos();

        // Verifying the expected behavior
        assertEquals(2, result.size());
        assertEquals("Table 1", result.get(0).getName());
        assertEquals("Table 2", result.get(1).getName());

        // Verifying that the TableRestoManageUseCase.getAllTables() method was called
        verify(tableRestoManageUseCase, times(1)).getAllTables();
    }

    @Test
    void testDeleteTable() {
        // Mocking the behavior of the TableRestoManageUseCase
        when(tableRestoManageUseCase.deleteTable(1)).thenReturn("Table deleted successfully");

        // Calling the controller method
        ResponseEntity<String> responseEntity = tableRestoController.deleteTable(1);

        // Verifying the expected behavior
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Table deleted successfully", responseEntity.getBody());

        // Verifying that the TableRestoManageUseCase.deleteTable() method was called
        verify(tableRestoManageUseCase, times(1)).deleteTable(1);
    }


}
