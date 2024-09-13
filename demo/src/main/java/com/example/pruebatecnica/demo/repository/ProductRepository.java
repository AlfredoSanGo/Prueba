package com.example.pruebatecnica.demo.repository;
import com.example.pruebatecnica.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySku(String sku);
}
