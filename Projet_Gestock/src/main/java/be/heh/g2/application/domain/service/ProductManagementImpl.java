package be.heh.g2.application.domain.service;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.port.in.ProductManagementUseCase;
import be.heh.g2.application.port.out.IProductRepository;

import java.util.List;

public class ProductManagementImpl implements ProductManagementUseCase {

    private IProductRepository product_repository;
   public ProductManagementImpl(IProductRepository product_repository){
        this.product_repository = product_repository;
    }
    @Override
    public List<Product> getAllProduct() {
        return product_repository.fetchAllProducts();
    }


    @Override
    public void createProduct(String name, double price, String category,int stock, String photo) {
        product_repository.CreateProductInRepository(name,price,category,stock,photo);
    }
    @Override
    public void createProduct(Product productToAdd){
        product_repository.CreateProductInRepository(productToAdd.getName(),productToAdd.getPrice(),productToAdd.getCategory(),productToAdd.getStock(),productToAdd.getPhoto());
    }

    @Override
    public void modifyProductQuantity(long id, int new_quantity) {
        product_repository.setQuantityInRepository(id,new_quantity);
    }
    @Override
    public void deleteProduct(Product product_to_delete){
        product_repository.removeProductFromRepository(product_to_delete);
    }


}
