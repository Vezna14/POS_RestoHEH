package be.heh.g2.application.port.in;

import be.heh.g2.application.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductManagementUseCase {

    public List<Product> getAllProduct();
    public Product getProductById(long id);


    public void createProduct(Product productToAdd);


    public void modifyProductQuantity(int id, int new_quantity);
    public int getProductStock(int productId);

    public void modifyProduct(InputProductValidator productToModify);


    public void deleteProduct(Product productToDelete);




}





