package fiap.com.controller;

import fiap.com.dto.OrderDTO;
import fiap.com.entity.OrderEntity;
import fiap.com.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc; // ✅ Mantém a injeção do MockMvc corretamente

    @MockBean
    private OrderService orderService; // ✅ Corrigido: Simulação correta do OrderService com @MockBean

    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // 🔹 Inicializa os mocks corretamente
        orderEntity = new OrderEntity(1L, 1L, 2, "Pending");
    }

    @Test
    void testGetOrderById() throws Exception {
        // ✅ Correção: O método retorna diretamente um OrderEntity, e não um Optional
        when(orderService.getOrderById(1L)).thenReturn(orderEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateOrderStatus() throws Exception {
        OrderDTO orderDTO = new OrderDTO(1L, 1L, 2, "Shipped"); // Criando DTO com novo status
        when(orderService.updateOrderStatus(eq(1L), anyString())).thenReturn(orderEntity);

        mockMvc.perform(MockMvcRequestBuilders.put("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\":1, \"productId\":1, \"quantity\":2, \"status\":\"Shipped\"}"))
                .andExpect(status().isOk());
    }


    @Test
    void testDeleteOrder() throws Exception {
        doNothing().when(orderService).deleteOrder(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/orders/1"))
                .andExpect(status().isOk());
    }
}
