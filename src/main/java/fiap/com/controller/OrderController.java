package fiap.com.controller;

import fiap.com.dto.OrderDTO;
import fiap.com.entity.OrderEntity;
import fiap.com.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return service.getAllOrders();
    }

    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderDTO orderDTO) {
        return service.createOrder(orderDTO);
    }

    @PutMapping("/{id}")
    public OrderEntity updateOrderStatus(@PathVariable Long id, @RequestBody String orderDTO) {
        return service.updateOrderStatus(id, orderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
    }
}