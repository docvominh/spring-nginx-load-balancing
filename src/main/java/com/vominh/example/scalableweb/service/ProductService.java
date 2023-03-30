package com.vominh.example.scalableweb.service;

import com.vominh.example.scalableweb.entity.ProductEntity;
import com.vominh.example.scalableweb.repo.IProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final IProductRepo repo;

    public ProductService(IProductRepo repo) {
        this.repo = repo;
    }


    public List<ProductEntity> findAll() {
        return repo.findAll();
    }

    @Transactional
    public ProductEntity create(ProductEntity entity) {
        return repo.save(entity);
    }
}
