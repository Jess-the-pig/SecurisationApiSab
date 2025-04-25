package be.ifapme.sab.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserOrderController {

    @GetMapping(path = "/orders")
    private UserResponse getAllUserOrders() {
        return UserResponse;
    }

    @GetMapping(path = "/orders/{orderId}")
    private UserResponse getOrderbyId() {
        return UserResponse;
    }


}
