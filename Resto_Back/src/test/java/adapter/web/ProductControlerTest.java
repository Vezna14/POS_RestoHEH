package adapter.web;
import be.heh.g2.adapter.web.ProductController;
import be.heh.g2.application.common.ProductNotFoundException;
import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.port.in.InputProductValidator;
import be.heh.g2.application.port.in.ProductManagementUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControlerTest {

    @Mock // Création automatique d'un mock avec l'annotation @Mock (besoin de créer des mocks pour les dépendances du controleur)
    private ProductManagementUseCase productManagementUseCase;


    @InjectMocks //@InjectMocks pour injecter automatiquement les mocks créés avec @Mock dans le contrôleur.
    private ProductController productController;


    private  MockMvc mockMvc; //MockMvc = classe fournie par Spring pour tester les contrôleurs (on peut simuler des requêtes HTTP)

    /*public ProductControlerTest() {
        //set up MockMvc pour tester un controleur spécifique (ici productController)
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }*/
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    //déroulement des test :
    //  1) Stubbing des méthodes appelées par le controleur (en utilisant when().thenReturn())
    //  2) Simuler la requête HTTP (en utilisant mockMvc.perform()) et vérification des résultats (en utilisant andExpect())
    //  3) Vériication des appels de méthodes (en utilisant verify())

    @Test
    public void testGetAllProducts() throws Exception {
        //1) Stubbing : renvoyer une liste de produits quand on appelle productManagementUseCase.getAllProduct()
        when(productManagementUseCase.getAllProduct()).thenReturn(new ArrayList<>(Arrays.asList(
                new Product(1, "Product1", 19.99, new ArrayList<>(Arrays.asList("test1")), 10, "test.jpg"),
                new Product(2, "Product2", 29.99, new ArrayList<>(Arrays.asList("test2")), 20, "test.jpg")
        )));
        //2) Simuler la requête HTTP et vérification des résultats
        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Product1"))
                .andExpect(jsonPath("$[0].price").value(19.99))
                .andExpect(jsonPath("$[0].category[0]").value("test1"))
                .andExpect(jsonPath("$[0].stock").value(10))
                .andExpect(jsonPath("$[0].photo").value("test.jpg"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Product2"))
                .andExpect(jsonPath("$[1].price").value(29.99))
                .andExpect(jsonPath("$[1].category[0]").value("test2"))
                .andExpect(jsonPath("$[1].stock").value(20))
                .andExpect(jsonPath("$[1].photo").value("test.jpg"));

        //3) Vérification des appels de méthodes
        Mockito.verify(productManagementUseCase, Mockito.times(1)).getAllProduct();
    }

    @Test
    public void testGetproductById() throws Exception {
        //1) Stubbing : renvoyer un produit quand on appelle productManagementUseCase.getProductById()
        when(productManagementUseCase.getProductById(1)).thenReturn(new Product(1, "Product1", 19.99, new ArrayList<>(Arrays.asList("test1")), 10, "test.jpg"));
        //2) Simuler la requête HTTP et vérification des résultats
        mockMvc.perform(get("/products/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Product1"))
                .andExpect(jsonPath("$.price").value(19.99))
                .andExpect(jsonPath("$.category[0]").value("test1"))
                .andExpect(jsonPath("$.stock").value(10))
                .andExpect(jsonPath("$.photo").value("test.jpg"));

        //3) Vérification des appels de méthodes
        Mockito.verify(productManagementUseCase, Mockito.times(1)).getProductById(1);

    }

    /*@Test
    public void testGetProductByIdNotFound() throws Exception {
        // Stubbing : lorsque productManagementUseCase.getProductById() est appelé avec un ID inexistant, lever une exception
        when(productManagementUseCase.getProductById(99L)).thenThrow(new ProductNotFoundException("Product not found with ID: 99"));

        // Effectuez la requête HTTP simulée et vérifiez les résultats
        mockMvc.perform(get("/products/99"))
                .andExpect(status().isNotFound());

        // Vérification que la méthode productManagementUseCase.getProductById() a été appelée avec l'ID 99
        Mockito.verify(productManagementUseCase, Mockito.times(1)).getProductById(99L);
    }*/

    @Test
    public void testCreateProduct() throws Exception {
        //1) Stubbing :
        Mockito.doNothing().when(productManagementUseCase).createProduct(any(Product.class));
        //2) Simuler la requête HTTP et vérification des résultats
        mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(asJsonString(new Product(1, "Product1", 19.99, new ArrayList<>(Arrays.asList("test1")), 10, "test.jpg"))))
                .andExpect(status().isOk())
                .andExpect(content().string("Product created successfully"));

        //3) Vérification des appels de méthodes
        Mockito.verify(productManagementUseCase, Mockito.times(1)).createProduct(any(Product.class));    }

   /* @Test
    public void testCreateProductFailure() throws Exception {
        //1) Stubbing :
        Mockito.doThrow(new Exception()).when(productManagementUseCase).createProduct(any(Product.class));
        //2) Simuler la requête HTTP et vérification des résultats
        mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(asJsonString(new Product(1, "Product1", 19.99, new ArrayList<>(Arrays.asList("test1")), 10, "test.jpg"))))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to create product"));

        //3) Vérification des appels de méthodes
        Mockito.verify(productManagementUseCase, Mockito.times(1)).createProduct(any(Product.class));
    }
    */





























    // Helper method to convert objects to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}