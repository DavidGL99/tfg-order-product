package ual.tfg.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "pedido")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial4")
    Long pedidoId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "producto_id")
    private Long productId;


    public Order(String userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }
}
