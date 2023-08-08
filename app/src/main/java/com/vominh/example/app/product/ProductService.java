package com.vominh.example.app.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository repo;
    private final ProductMapper mapper;

    public List<Product> findAll() {
        return mapper.toProducts(repo.findAll());
    }

    @Transactional
    public Product create(Product product) {
        var entity = repo.save(mapper.toProductEntity(product));
        return mapper.toProduct(entity);
    }
}
