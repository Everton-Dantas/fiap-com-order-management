package fiap.com;

import fiap.com.dto.OrderDTO;
import fiap.com.entity.OrderEntity;
import fiap.com.repository.OrderRepository;
import fiap.com.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderDTO orderDTO;
    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderDTO = new OrderDTO(1L, 1L, 2, "Pending");
        orderEntity = new OrderEntity(1L, 1L, 2, "Pending");
    }

    @Test
    void testUpdateOrderStatus() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));
        when(orderRepository.save(any(OrderEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        orderDTO.setStatus("Shipped");
        OrderEntity updatedOrder = orderService.updateOrderStatus(1L, String.valueOf(orderDTO));

        assertEquals("Shipped", updatedOrder.getStatus());
    }
}