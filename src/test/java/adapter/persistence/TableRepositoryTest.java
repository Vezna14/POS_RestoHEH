package adapter.persistence;

import be.heh.g2.adapter.persistence.TableRepository;
import be.heh.g2.adapter.persistence.TableRestoRowMapper;
import be.heh.g2.application.domain.model.TableResto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@JdbcTest
class TableRepositoryTest {

    @MockBean
    private JdbcTemplate jdbcTemplate;

    private TableRepository tableRepository;

    @BeforeEach
    void setUp() {
        tableRepository = new TableRepository(jdbcTemplate);
    }

    @Test
    void testFindAllTables() {
        // Mock the behavior of JdbcTemplate
        when(jdbcTemplate.query(anyString(), any(TableRestoRowMapper.class)))
                .thenReturn(List.of(new TableResto(1, "Table 1","occupé",12,false)));

        List<TableResto> tables = tableRepository.findAllTables();

        // Assertions
        assertEquals(1, tables.size());
        // Add more assertions based on your requirements
    }

    @Test
    void testDeleteTable() {
        int idTable = 1;

        // Mock the behavior of JdbcTemplate
        doNothing().when(jdbcTemplate).update(anyString(), eq(idTable));

        String result = tableRepository.deleteTable(idTable);

        // Assertions
        assertEquals("success", result);
        // Add more assertions based on your requirements
    }

    @Test
    void testCreateTable() {
        TableResto table = new TableResto(1, "Table 1", "occupe", 12, false);

        // Mock the behavior of JdbcTemplate
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyString()))
                .thenReturn(0); // Simulate that the table does not exist
        doNothing().when(jdbcTemplate).update(anyString(), any(), any(), any(), any());

        String result = tableRepository.createTable(table);

        // Assertions
        assertEquals("Table added successfully!", result);
        // Add more assertions based on your requirements
    }

    @Test
    void testToggleStatusTable() {
        int idTable = 1;

        // Mock the behavior of JdbcTemplate
        when(jdbcTemplate.queryForObject(anyString(), eq(String.class), eq(idTable)))
                .thenReturn("available"); // Simulate that the current status is available
        doNothing().when(jdbcTemplate).update(anyString(), any(), eq(idTable));

        tableRepository.toggleStatutTable(idTable);

        // Add assertions based on your requirements
    }

    // Add more tests as needed

}
