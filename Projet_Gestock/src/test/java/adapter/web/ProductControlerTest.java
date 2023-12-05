import be.heh.g2.adapter.web.ProductController;
import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.port.in.ProductManagementUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControlerTest {

    @Mock
    private ProductManagementUseCase productManagementUseCase;

    @InjectMocks
    private ProductController productController;

    private final MockMvc mockMvc;

    public ProductControlerTest() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testCreateProductSuccess() throws Exception {
        // Prepare input product
        Product productToAdd = new Product(12,"controllerProduct", 29.99, new ArrayList<>(Arrays.asList("test1")), 50, "test.jpg");

        // Mock the behavior of productManagementUseCase.createProduct
        Mockito.doNothing().when(productManagementUseCase).createProduct(productToAdd);

        // Perform the request
        MvcResult result = mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productToAdd)))
                .andExpect(status().isOk())
                .andExpect(content().string("Product created successfully"))
                .andReturn();

        // Perform additional assertions if needed
    }

    @Test
    public void testCreateProductFailure() throws Exception {
        // Prepare input product
        Product productToAdd = new Product(23,"controllerFailProduct", 29.99, new ArrayList<>(Arrays.asList("test1")), 50, "test.jpg");

        // Mock the behavior of productManagementUseCase.createProduct to throw an exception
        Mockito.doThrow(new RuntimeException("Some error")).when(productManagementUseCase).createProduct(productToAdd);

        // Perform the request
        MvcResult result = mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productToAdd)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to create product"))
                .andReturn();

        // Perform additional assertions if needed
    }

    // Helper method to convert objects to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}