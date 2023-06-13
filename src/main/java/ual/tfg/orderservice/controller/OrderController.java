package ual.tfg.orderservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ual.tfg.orderservice.config.PayPalHttpClient;
import ual.tfg.orderservice.dto.OrderDTO;
import ual.tfg.orderservice.dto.PayPalAppContextDTO;
import ual.tfg.orderservice.dto.PaypalOrderResponseDTO;
import ual.tfg.orderservice.entity.Order;
import ual.tfg.orderservice.entity.PaypalOrder;
import ual.tfg.orderservice.repository.PaypalOrderRespository;
import ual.tfg.orderservice.service.OrderService;
import ual.tfg.orderservice.service.SecurityService;
import ual.tfg.orderservice.dto.OrderRequestDto;


import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    SecurityService securityService;

    @Autowired
    private PayPalHttpClient payPalHttpClient;
    @Autowired
    private PaypalOrderRespository orderDAO;

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

    @PostMapping("/checkout")
    public ResponseEntity<PaypalOrderResponseDTO> checkout(@RequestBody OrderDTO orderDTO) throws Exception {
        var appContext = new PayPalAppContextDTO();
        appContext.setReturnUrl("http://localhost:4200/cart?success=true");
        appContext.setBrandName("MarketTech");
        appContext.setLandingPage("LOGIN");
        orderDTO.setApplicationContext(appContext);
        var orderResponse = payPalHttpClient.createOrder(orderDTO);

        var entity = new PaypalOrder();
        entity.setPaypalOrderId(orderResponse.getId());
        entity.setPaypalOrderStatus(orderResponse.getStatus());
        var out = orderDAO.save(entity);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping(value = "/success")
    public ResponseEntity paymentSuccess(HttpServletRequest request) {
        var orderId = request.getParameter("token");
        var out = orderDAO.findByPaypalOrderId(orderId);
        out.setPaypalOrderStatus("Approved");
        orderDAO.save(out);
        return ResponseEntity.ok().build();
    }


}
