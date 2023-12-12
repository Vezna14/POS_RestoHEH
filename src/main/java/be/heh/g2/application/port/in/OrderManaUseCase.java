package be.heh.g2.application.port.in;

import be.heh.g2.application.domain.model.Order;

public interface OrderManaUseCase {

    public String makeSaveOrder(Order order);
    public String makePayOrder(int idTable);
    public Order makeShowOrder(int idTable);
}
