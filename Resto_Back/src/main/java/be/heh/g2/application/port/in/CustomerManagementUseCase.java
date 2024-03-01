package be.heh.g2.application.port.in;

import be.heh.g2.application.domain.model.Product;
import be.heh.g2.application.domain.model.Customer;

import java.util.List;

public interface CustomerManagementUseCase {

    public List<Customer> getAllCustomers();

    public Customer GetCustomerById();

    public void createCustomer(String name, double price, String category, int stock, String photo);

    public void Modify(long id, int new_quantity);

    public void deleteProduct(Product productToDelete);

}
