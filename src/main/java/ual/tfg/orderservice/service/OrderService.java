package ual.tfg.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ual.tfg.orderservice.dto.OrderRequestDto;
import ual.tfg.orderservice.dto.OrderResponseDto;
import ual.tfg.orderservice.dto.ProductDto;
import ual.tfg.orderservice.entity.Order;
import ual.tfg.orderservice.feignclients.ProductFeignClient;
import ual.tfg.orderservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductFeignClient productFeignClient;


    public boolean save(OrderRequestDto order) {
        for(Long o : order.getProductId()){
            orderRepository.save(new Order(order.getUserId(), o));
        }
        return true;
    }

    public List<OrderResponseDto> getAllById(String id){
        List<Order> cartList = orderRepository.findAllByUserId(id);
        List<OrderResponseDto> response = new ArrayList<>();
        for(Order cart : cartList){
            response.add(new OrderResponseDto(cart.getUserId(), getProduct(cart.getProductId())));
        }
        return  response;
    }
    public ProductDto getProduct(Long productId){
        try {
            ProductDto response = productFeignClient.getById(productId).getBody();
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
