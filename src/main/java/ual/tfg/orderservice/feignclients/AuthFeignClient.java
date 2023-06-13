package ual.tfg.orderservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ual.tfg.orderservice.dto.TokenDto;

@FeignClient(name = "auth-service")
public interface AuthFeignClient {

    @PostMapping("/auth/validate")
    ResponseEntity<TokenDto> validate(@RequestBody TokenDto token);
}
