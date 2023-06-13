package ual.tfg.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ual.tfg.orderservice.entity.PaypalOrder;

@Repository
public interface PaypalOrderRespository extends JpaRepository<PaypalOrder, Long> {
    PaypalOrder findByPaypalOrderId(String paypalOrderId);

}