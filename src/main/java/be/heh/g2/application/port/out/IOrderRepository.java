package be.heh.g2.application.port.out;

import be.heh.g2.application.domain.model.Order;

public interface IOrderRepository {
    public void saveOrderRepo(Order order);
    public void payOrderRepo(int idTable);
    public Order showOrderRepo(int idTable);
}
