package be.heh.g2.application.port.out;

import be.heh.g2.application.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    public List<Product> fetchAllProducts();
    public void CreateProductInRepository(String name, double price, String category,int stock, String photo);

    public void setQuantityInRepository(long id, int newQuantity);

    public void removeProductFromRepository(Product productToRemove);

}
