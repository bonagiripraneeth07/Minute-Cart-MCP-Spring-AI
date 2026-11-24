package com.minuteCart.app.repo;

import com.minuteCart.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

     Product findByProductNameIgnoreCase(String productName);

     List<Product> findByProductNameContainingIgnoreCase(String productName);

     List<Product> findByCategoryIgnoreCase(String category);

     List<Product> findByBrandIgnoreCase(String brandName);
}
