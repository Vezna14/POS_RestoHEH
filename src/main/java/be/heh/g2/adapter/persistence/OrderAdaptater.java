package be.heh.g2.adapter.persistence;

import be.heh.g2.application.domain.model.Order;
import be.heh.g2.application.port.out.IOrderRepository;

public class OrderAdaptater implements IOrderRepository {
    private final OrderRepository orderRepository;

    public OrderAdaptater(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrderRepo(Order order){
        orderRepository.saveOrder(order);

    }
    @Override
    public void payOrderRepo(int idTable){
        orderRepository.payOrder(idTable);
    }
    @Override
    public Order showOrderRepo(int idTable){
        Order order=orderRepository.showOrder(idTable);
     return order;
    }

}
