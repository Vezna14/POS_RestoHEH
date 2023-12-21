package be.heh.g2.application.domain.service;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.port.in.InputProductValidator;
import be.heh.g2.application.port.in.ProductManagementUseCase;
import be.heh.g2.application.port.out.IProductRepository;

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
    public void modifyProduct(InputProductValidator productToModify) {
        Product ProductToUpdate= new Product(productToModify.id(), productToModify.name(), productToModify.price(), productToModify.category(), productToModify.stock(), productToModify.photo());
        product_repository.modifyProductInRepository(ProductToUpdate);
    }
    @Override
    public void deleteProduct(Product product_to_delete){
        product_repository.removeProductFromRepository(product_to_delete);
    }


}
