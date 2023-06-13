package ual.tfg.orderservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {

    private Long id;
    private String name;
    private String price;
    private String url;
    private String categoria;
}
