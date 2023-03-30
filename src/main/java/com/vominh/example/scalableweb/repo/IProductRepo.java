package com.vominh.example.scalableweb.repo;

import com.vominh.example.scalableweb.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends JpaRepository<ProductEntity, Integer> {
}
