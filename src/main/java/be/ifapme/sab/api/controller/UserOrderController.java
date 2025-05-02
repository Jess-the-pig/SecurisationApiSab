package be.ifapme.sab.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserOrderController {

    @GetMapping(path = "/orders")
    private UserResponse getAllUserOrders() {
        return UserResponse;
    }

    @GetMapping(path = "/orders/{orderId}")
    private UserResponse getOrderbyId() {
        return UserResponse;
    }

    @GetMapping(path = "/orders/{userId}")
    private UserResponse getOrderbyuser() {
        return UserResponse;
    }


}
