package be.heh.g2.application.domain.service;

import be.heh.g2.adapter.persistence.OrderRepository;
import be.heh.g2.application.domain.model.Order;
import be.heh.g2.application.domain.model.ProductOrder;
import be.heh.g2.application.port.in.OrderManaUseCase;
import be.heh.g2.application.port.out.IOrderRepository;
import be.heh.g2.application.port.out.IProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class OrderManageImpl implements OrderManaUseCase {
    private final IOrderRepository orderRepository;
    private final IProductRepository productRepository;

    public OrderManageImpl(IOrderRepository orderRepository,IProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository=productRepository;
    }

    public String makeSaveOrder(Order order) {
        orderRepository.saveOrderRepo(order);
        System.out.println("make save  order liste  "+order);
        return "Order created successfully";
    }

    public ResponseEntity<String> makePayOrder(int idTable, Order order) {


        // Parcourir la liste des produits dans la commande
        for (ProductOrder product : order.getProductList()) {
            int productId = product.getId();
            int quantityOrdered = product.getQuantity();

            // Vérifier si le stock est suffisant
            int currentStock = productRepository.getProductStock(productId);

            if (currentStock >= quantityOrdered) {
                // Mettre à jour le stock dans la base de données
                int newStock = currentStock - quantityOrdered;
                productRepository.setQuantityInRepository(productId, newStock);
            } else {
                // Stock insuffisant, renvoyer un code de statut 400 (Bad Request) avec un message explicatif
                return new ResponseEntity<>("Stock insuffisant pour le produit: " + product.getName(), HttpStatus.BAD_REQUEST);
            }
        }

        orderRepository.saveOrderRepo(order);
        orderRepository.payOrderRepo(idTable);
        System.out.println("brut order liste: " + order);

        // Renvoyer un code de statut 200 (OK) avec un message de succès
        return new ResponseEntity<>("Order paid successfully", HttpStatus.OK);
    }


    public Order makeShowOrder(int idTable) {
        return orderRepository.showOrderRepo(idTable);
    }
}

