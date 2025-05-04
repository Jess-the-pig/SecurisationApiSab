package be.ifapme.sab.api.controller;

import be.ifapme.sab.api.DTO.OrderResponse;
import be.ifapme.sab.model.entities.Order;
import be.ifapme.sab.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class UserOrderController {
    private OrderService orderService;

    @GetMapping(path = "/orders")
    @ResponseStatus(HttpStatus.FOUND)
    private List<OrderResponse> getAllUserOrders() {
        return orderService.getAllOrders();
    }


}
