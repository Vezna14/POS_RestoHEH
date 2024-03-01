package application.domain.service;


import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.domain.service.ProductManagementImpl;
import be.heh.g2.application.port.in.InputProductValidator;
import be.heh.g2.application.port.out.IProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductManagerImplTest {
    public static List<Product> products_list;
    public static ArrayList<String> category_list;
    private ProductManagementImpl product_manager_impl;
    private IProductRepository product_repository;

    @BeforeEach
    public void setup() {
        //créer un liste de produit pour simuler bdd ( que 2 produits )
        category_list= new ArrayList<>(Arrays.asList("boisson","soft"));
        products_list = new ArrayList<>(Arrays.asList(new Product(1,"product1", 2.5,category_list,1,"srcImg"),new Product(2,"product2", 3,category_list,1,"srcImg")));
        product_repository = Mockito.mock(IProductRepository.class); //Bouchon
        product_manager_impl = new ProductManagementImpl(product_repository);

    }
    @Test
    void shouldReturnAllProduct() {
        when(product_repository.fetchAllProducts()).thenReturn(products_list);

        //tester les méthodes
        assertEquals(products_list, product_manager_impl.getAllProduct());
        List<Product> result = product_manager_impl.getAllProduct();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("product1", result.get(0).getName());
        assertEquals("product2", result.get(1).getName());

    }


    @Test
    void ShouldAddProductInRepository () {
        category_list= new ArrayList<>(Arrays.asList("boisson","soft"));
        Product testProduct = new Product(1,"product1", 2.5,category_list,1,"srcImg");
        // Appeler la méthode
        product_manager_impl.createProduct(testProduct);
        // Vérifier que la méthode d'ajout a été appelée avec le produit à ajouter
        verify(product_repository).CreateProductInRepository(testProduct);

    }


    @Test
    public void ShouldRemoveProductFromRepository(){
        // Données de test
        category_list= new ArrayList<>(Arrays.asList("boisson","soft"));
        Product productToDelete = new Product(1,"product1", 10.0, category_list,10,"srcImg");
        // Appeler la méthode
        product_manager_impl.deleteProduct(productToDelete);
        // Vérifier que la méthode de suppression a été appelée avec le produit à supprimer
        verify(product_repository).removeProductFromRepository(productToDelete);
    }

    /*@Test
    public void ShouldModifyProduct(){

        category_list= new ArrayList<>(Arrays.asList("boisson","soft"));
        InputProductValidator modifiedProduct = new InputProductValidator(1,"product1", 2.5,category_list,1,"srcImg");
        product_manager_impl.modifyProduct(modifiedProduct);
        Product prod = new Product(1,"product1", 2.5,category_list,1,"srcImg");
        verify(product_repository).modifyProductInRepository(prod);

    }*/
}



