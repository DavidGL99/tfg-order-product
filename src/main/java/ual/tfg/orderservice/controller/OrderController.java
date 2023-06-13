package ual.tfg.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ual.tfg.orderservice.entity.Order;
import ual.tfg.orderservice.service.SecurityService;
import ual.tfg.orderservice.dto.OrderRequestDto;
import ual.tfg.orderservice.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    SecurityService securityService;

    @PostMapping("/save")
    private ResponseEntity<Order> saveCart(@RequestBody OrderRequestDto order, @RequestHeader("Authorization") String token) {
        if (!securityService.validate(token.split("Bearer")[1])) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    private ResponseEntity<List> getCart(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        if (!securityService.validate(token.split("Bearer")[1])) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(orderService.getAllById(id));
    }

}
