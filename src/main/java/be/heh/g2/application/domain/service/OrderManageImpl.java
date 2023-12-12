package be.heh.g2.application.domain.service;

import be.heh.g2.adapter.persistence.OrderRepository;
import be.heh.g2.application.domain.model.Order;
import be.heh.g2.application.port.in.OrderManaUseCase;
import be.heh.g2.application.port.out.IOrderRepository;

public class OrderManageImpl implements OrderManaUseCase {
    private final IOrderRepository orderRepository;

    public OrderManageImpl(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String makeSaveOrder(Order order) {
        orderRepository.saveOrderRepo(order);
        return "Order created successfully";
    }

    public String makePayOrder(int idTable) {
        orderRepository.payOrderRepo(idTable);
        return "Order paid successfully";
    }

    public Order makeShowOrder(int idTable) {
        return orderRepository.showOrderRepo(idTable);
    }
}

