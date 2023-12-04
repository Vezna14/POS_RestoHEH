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
    public void CreateProductInRepository(String name, double price, ArrayList<String> category, int stock, String photo) {

    }

    @Override
    public void CreateProductInRepository(String name, double price, String category, int stock, String photo) {

    }

    @Override
    public void setQuantityInRepository(long id, int newQuantity) {

    }

    @Override
    public void removeProductFromRepository(Product productToRemove) {

    }
}
