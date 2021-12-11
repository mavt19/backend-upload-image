package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
