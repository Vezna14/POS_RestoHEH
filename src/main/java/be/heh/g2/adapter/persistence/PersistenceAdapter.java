package be.heh.g2.adapter.persistence;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.port.out.IProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;


import java.util.ArrayList;
import java.util.List;

public class PersistenceAdapter implements IProductRepository {
    private ProductRepository productRepository;

    public PersistenceAdapter(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> fetchAllProducts() {

        return  productRepository.findAllProducts();
    }

    @Override
    public Product fetchProductById(long id){
        List<Product> result = productRepository.findProductById(id);
        return result.get(0);
    }


    @Override
    public void CreateProductInRepository(Product newProduct) {
        try {
            //appel de la methode du repository pour save le product
            productRepository.storeProductinDB(newProduct);

            // Log
            System.out.println("Product added successfully: " + newProduct);
        } catch (Exception e) {
            // Log
            System.err.println("Failed to add product: " + e.getMessage());
        }

    }

    @Override

    public void setQuantityInRepository(int id, int newQuantity) {
    }

    public void modifyProductInRepository(Product productToModify) {
        productRepository.modifyProductInDB(productToModify);

    }

    @Override
    public void removeProductFromRepository(Product productToRemove) {
        productRepository.removeProduct(productToRemove.getId());
        }



    @Override
    public int getProductStock(int productId){
        try {
            //appel de la methode du repository pour save le product
            return  productRepository.getProductStock(productId);
        } catch (EmptyResultDataAccessException e) {
            // Gérer le cas où aucun produit avec l'ID spécifié n'est trouvé
            throw new RuntimeException("Product with ID " + productId + " not found");
        }

    }

    @Override
    public List<Product> fetchProductRecommandation(String foodType) {
        return productRepository.findProductByFoodType(foodType);
    }
}
