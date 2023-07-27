package com.vominh.example.app.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.config.ServiceTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ServiceTest
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(ProductEntity.builder()
            .category("computer")
            .name("Dell xps 17")
            .manufacture("Dell")
            .price(BigDecimal.valueOf(750))
            .build());

        repository.save(ProductEntity.builder()
            .category("computer")
            .name("Apple Macbook 15")
            .manufacture("Apple")
            .price(BigDecimal.valueOf(1200))
            .build());
    }

    @Test
    void testFindAll() {
        var products = service.findAll();
        assertThat(products).hasSize(2)
            .extracting("name").asList().contains("Dell xps 17", "Apple Macbook 15");
    }
}
