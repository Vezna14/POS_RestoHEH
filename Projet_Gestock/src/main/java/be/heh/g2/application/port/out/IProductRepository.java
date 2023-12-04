package be.heh.g2.application.port.out;

import be.heh.g2.application.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface IProductRepository {

    public List<Product> fetchAllProducts();
    //public Product fetchProductById()
    public void CreateProductInRepository(String name, double price, ArrayList<String> category, int stock, String photo);
    public void CreateProductInRepository(String name, double price, String category, int stock, String photo);

    public void setQuantityInRepository(long id, int newQuantity);

    public void removeProductFromRepository(Product productToRemove);

}
