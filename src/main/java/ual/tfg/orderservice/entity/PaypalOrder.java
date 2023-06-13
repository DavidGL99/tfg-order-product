package ual.tfg.orderservice.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "paypalorders")
@Data
public class PaypalOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "paypal_order_id")
    private String paypalOrderId;
    @Column(name = "paypal_order_status")
    private String paypalOrderStatus;
}