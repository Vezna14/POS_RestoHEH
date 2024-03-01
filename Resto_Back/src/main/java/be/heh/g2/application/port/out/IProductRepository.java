package be.heh.g2.application.port.out;

import be.heh.g2.application.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface IProductRepository {

    public List<Product> fetchAllProducts();
    //public Product fetchProductById()

    public Product fetchProductById(long id);
    public void CreateProductInRepository(Product product);


    public void setQuantityInRepository(int id, int newQuantity);

    public void modifyProductInRepository(Product productToModify);


    public void removeProductFromRepository(Product productToRemove);
    public int getProductStock(int productId);

    public List<Product> fetchProductRecommandation(String foodType);

}
