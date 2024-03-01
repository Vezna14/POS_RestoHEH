package be.heh.g2.application.domain.service;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.port.in.InputProductValidator;
import be.heh.g2.application.port.in.ProductManagementUseCase;
import be.heh.g2.application.port.out.IProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProductManagementImpl implements ProductManagementUseCase {

    private final IProductRepository product_repository;

    public ProductManagementImpl(IProductRepository product_repository) {
        this.product_repository = product_repository;
    }

    @Override
    public List<Product> getAllProduct() {
        return product_repository.fetchAllProducts();
    }

    @Override
    public Product getProductById(long id){return product_repository.fetchProductById(id);}

    @Override
    public void createProduct(Product productToAdd) {
        product_repository.CreateProductInRepository(productToAdd);
    }

    @Override
    public void modifyProductQuantity(int id, int new_quantity) {
        product_repository.setQuantityInRepository(id, new_quantity);
    }

    @Override
    public void modifyProduct(InputProductValidator productToModify) {
        Product ProductToUpdate= new Product(productToModify.id(), productToModify.name(), productToModify.price(), productToModify.category(), productToModify.stock(), productToModify.photo());
        product_repository.modifyProductInRepository(ProductToUpdate);

    }
    @Override
    public void deleteProduct(Product product_to_delete){
        product_repository.removeProductFromRepository(product_to_delete);
    }

    @Override
    public List<Product> getProductRecommandation() {
        // Appel à l'API OpenWeatherMap
        String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?q=mons&unit=Metric&appid=0b46c6f7971264a4fbfd44c39d6477d1";
        RestTemplate restTemplate = new RestTemplate();
        String weatherResponse = restTemplate.getForObject(weatherApiUrl, String.class);

        // Utiliser Jackson pour extraire la température
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(weatherResponse);
            int temperatureKelvin = jsonNode.path("main").path("temp").asInt();

            // Convertir de Kelvin à Celsius
            int temperatureCelsius = temperatureKelvin - 273;
            if (temperatureCelsius < 12) {
                return product_repository.fetchProductRecommandation("chaud");
            }else
                return product_repository.fetchProductRecommandation("froid");

        } catch (Exception e) {
            e.printStackTrace();
            return product_repository.fetchAllProducts();
        }
    }

    @Override
    public int getProductStock(int productId){
        return product_repository.getProductStock(productId);
    }


}
