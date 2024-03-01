package adapter.persistence;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import be.heh.g2.adapter.persistence.TableRepository;
import be.heh.g2.adapter.persistence.TableRestoRowMapper;
import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.domain.model.TableResto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

class TableRepositoryTest {

    private JdbcTemplate jdbcTemplate;
    private TableRepository tableRepository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        tableRepository = new TableRepository(jdbcTemplate);


    }

    @Test
    void testCreateTable() {
        // Arrange
        TableResto table = new TableResto(1, "Table 1", "occupe", 12, false);
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyString()))
                .thenReturn(0); // Simulate that the table does not exist

        // Act
        String result = tableRepository.createTable(table);


        // Assert
        assertEquals("Table added successfully!", result);

        // Verify that the update method is called with the expected parameters
        verify(jdbcTemplate).update(anyString(),  eq("Table 1"), eq("occupe"), eq(12), eq(false));
    }
    @Test
    void testFindAllTables() {
        // Mock the behavior of JdbcTemplate
        List<TableResto> mockTables = Arrays.asList(new TableResto(1, "Table 3", "status", 4, false));
        when(jdbcTemplate.query(eq("SELECT * FROM TableResto"), any(TableRestoRowMapper.class))).thenReturn(mockTables);

        // Call the findAllTables method
        List<TableResto> result = tableRepository.findAllTables();

        // Verify that the jdbcTemplate.query method was called with the correct arguments
        verify(jdbcTemplate, times(1)).query(eq("SELECT * FROM TableResto"), any(TableRestoRowMapper.class));

        // Assertions
        assertNotNull(result);
        assertFalse(result.isEmpty());


        assertEquals("Table 3", result.get(0).getName());

    }



    @Test
    void testDeleteTable() {
        // Mock the behavior of JdbcTemplate
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyString()))
                .thenReturn(1);

        // Call the deleteTable method
        String result = tableRepository.deleteTable(1);

        // Assertions
        assertEquals("success", result);

        // Verify that the jdbcTemplate.update method was called with the correct arguments
        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM  TableResto WHERE id=?"), eq(1));
    }



    @AfterEach
    void tearDown() {
        // Reset mocks or perform cleanup if needed
    }
}