package be.heh.g2.application.port.in;

import be.heh.g2.application.domain.model.Order;
import org.springframework.http.ResponseEntity;

public interface OrderManaUseCase {

    public String makeSaveOrder(Order order);
    public ResponseEntity<String> makePayOrder(int idTable, Order order);
    public ResponseEntity<String>makePayOrderById(int id);
    public Order makeShowOrder(int idTable);
}
