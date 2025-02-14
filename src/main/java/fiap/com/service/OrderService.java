package fiap.com.service;

import fiap.com.dto.OrderDTO;
import fiap.com.entity.OrderEntity;
import fiap.com.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    // ✅ Obtém todas as ordens
    public List<OrderEntity> getAllOrders() {
        return repository.findAll();
    }

    // ✅ Criação de uma nova ordem com validação
    public OrderEntity createOrder(OrderDTO orderDTO) {
        if (orderDTO.getCustomerId() == null || orderDTO.getProductId() == null || orderDTO.getQuantity() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order data");
        }
        OrderEntity order = new OrderEntity(
                orderDTO.getCustomerId(),
                orderDTO.getProductId(),
                orderDTO.getQuantity(),
                orderDTO.getStatus()
        );
        return repository.save(order);
    }

    // ✅ Atualiza o status da ordem com validação
    public OrderEntity updateOrderStatus(Long id, String status) {
        OrderEntity existingOrder = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        OrderEntity orderDTO = new OrderEntity();
        existingOrder.setStatus(orderDTO.getStatus());
        return repository.save(existingOrder);
    }

    // ✅ Deleta uma ordem com validação
    public void deleteOrder(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        repository.deleteById(id);
    }

    // ✅ Obtém uma ordem por ID com tratamento de erro
    public OrderEntity getOrderById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }
}
