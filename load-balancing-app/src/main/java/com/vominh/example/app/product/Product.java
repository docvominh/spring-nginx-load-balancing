package com.vominh.example.app.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String category;
    private String name;
    private String manufacture;
    private BigDecimal price;
}
