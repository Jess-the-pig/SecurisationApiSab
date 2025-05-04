package be.ifapme.sab.service;

import be.ifapme.sab.api.DTO.OrderResponse;
import be.ifapme.sab.api.utils.SecurityUtils;
import be.ifapme.sab.model.entities.Cart;
import be.ifapme.sab.model.entities.Order;
import be.ifapme.sab.model.entities.OrderItem;
import be.ifapme.sab.model.entities.Payment;
import be.ifapme.sab.model.entities.enums.OrderStatus;
import be.ifapme.sab.repository.CartRepository;
import be.ifapme.sab.repository.OrderRepository;
import be.ifapme.sab.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;


    @Transactional
    public Order convertCartToOrder(Cart cart, Payment payment) {
        log.info("Conversion du panier vers commande");
        Order order = new Order();
        order.setPerson(cart.getUser());
        order.setPayment(payment);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PAID);
        order.setTotalAmount(cart.calculateTotalAmount());

        // Créer des OrderItems basés sur les CartItems
        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getPrice());
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        order.setItems(orderItems);

        // Sauvegarder la commande dans la base de données
        return orderRepository.save(order);
    }

    @Transactional
    public Order save(Order order) {

        log.info("Enregistrement de la commande");
        return orderRepository.save(order);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderResponse> getAllOrders(){
        log.info("Recherche des commandes");
        SecurityUtils.checkAdmin();
        List<Order> allOrderList = orderRepository.findAll();

        return allOrderList.stream()
                .map(item -> new OrderResponse(
                        item.getPerson(),
                        item.getCart(),
                        item.getPayment(),
                        item.getTotalAmount(),
                        item.getStatus()
                ))
                .toList();

    }
}
