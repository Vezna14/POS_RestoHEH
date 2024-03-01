package adapter.web;


import be.heh.g2.adapter.web.OrderController;
import be.heh.g2.application.domain.model.Order;
import be.heh.g2.application.domain.model.ProductOrder;
import be.heh.g2.application.port.in.OrderManaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderManaUseCase orderUseCase;

    private OrderController orderController;
    private Order mockOrder; // Déclaration de mockOrder comme variable d'instance

    @BeforeEach
    public void setUp() {
        orderController = new OrderController(orderUseCase);
        List<ProductOrder> productList = Arrays.asList(
                new ProductOrder(1, "Product 1", 10.0, 2),
                new ProductOrder(2, "Product 2", 15.0, 1)
        );
        mockOrder = new Order(123, 456, productList, false, 35.0, "2023-01-24");
    }

    @Test
    public void testCreateOrder_Success() {
        when(orderUseCase.makeSaveOrder(mockOrder)).thenReturn("Order created successfully");
        ResponseEntity<String> response = orderController.createOrder(mockOrder);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Order created successfully", response.getBody());
        verify(orderUseCase).makeSaveOrder(mockOrder);
    }

    @Test
    public void testCreateOrder_Failure() {
        when(orderUseCase.makeSaveOrder(mockOrder)).thenThrow(new RuntimeException("Error occurred"));
        ResponseEntity<String> response = orderController.createOrder(mockOrder);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error creating order: Error occurred", response.getBody());
        verify(orderUseCase).makeSaveOrder(mockOrder);
    }


    @Test
    public void testPayOrder_Success() {
        ResponseEntity<String> mockResponse = new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
        when(orderUseCase.makePayOrder(mockOrder.getIdTable(), mockOrder)).thenReturn(mockResponse);
        ResponseEntity<String> response = orderController.payOrder(mockOrder);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Order created successfully", response.getBody());
        verify(orderUseCase).makePayOrder(mockOrder.getIdTable(), mockOrder);
    }

    @Test
    public void testPayOrderById_Success() {
        int orderId = mockOrder.getId();
        ResponseEntity<String> mockResponse = new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
        when(orderUseCase.makePayOrderById(orderId)).thenReturn(mockResponse);
        ResponseEntity<String> response = orderController.payOrderById(orderId);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Order created successfully", response.getBody());
        verify(orderUseCase).makePayOrderById(orderId);
    }



    @Test
    public void testPayOrder_Failure() {
        doThrow(new RuntimeException("Error paying order")).when(orderUseCase).makePayOrder(mockOrder.getIdTable(), mockOrder);
        ResponseEntity<String> response = orderController.payOrder(mockOrder);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error pay order: Error paying order", response.getBody());
        verify(orderUseCase).makePayOrder(mockOrder.getIdTable(), mockOrder);
    }



    @Test
    public void testPayOrderById_Failure() {
        int orderId = mockOrder.getId();
        doThrow(new RuntimeException("Error paying order")).when(orderUseCase).makePayOrderById(orderId);
        ResponseEntity<String> response = orderController.payOrderById(orderId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error pay order: Error paying order", response.getBody());
        verify(orderUseCase).makePayOrderById(orderId);
    }

    @Test
    public void testShowOrder() {
        int tableId = mockOrder.getIdTable();
        when(orderUseCase.makeShowOrder(tableId)).thenReturn(mockOrder);
        Order response = orderController.showOrder(tableId);
        assertEquals(mockOrder, response);
        verify(orderUseCase).makeShowOrder(tableId);
    }

}
