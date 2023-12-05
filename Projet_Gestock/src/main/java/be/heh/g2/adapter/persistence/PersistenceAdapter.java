package be.heh.g2.adapter.persistence;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.port.out.IProductRepository;


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
    public void setQuantityInRepository(long id, int newQuantity) {

    }

    @Override
    public void removeProductFromRepository(Product productToRemove) {

    }
}
