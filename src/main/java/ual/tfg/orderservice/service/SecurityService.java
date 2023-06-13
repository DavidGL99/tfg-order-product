package ual.tfg.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ual.tfg.orderservice.dto.TokenDto;
import ual.tfg.orderservice.feignclients.AuthFeignClient;

@Service
public class SecurityService {

    @Autowired
    AuthFeignClient authFeignClient;

    public boolean validate(String jwt){
        try {
            authFeignClient.validate(new TokenDto(jwt));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
