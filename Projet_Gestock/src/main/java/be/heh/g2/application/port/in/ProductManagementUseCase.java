package be.heh.g2.application.port.in;

import be.heh.g2.application.domain.model.Product;

import java.util.List;

public interface ProductManagementUseCase {

    public List<Product> getAllProduct();

    public void createProduct(String name, double price, String category, int stock, String photo);
    public void createProduct(Product productToAdd);

    public void modifyProductQuantity(long id, int new_quantity);

    public void deleteProduct(Product productToDelete);




}





