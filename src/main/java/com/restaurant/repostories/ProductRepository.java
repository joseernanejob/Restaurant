package com.restaurant.repostories;

import com.restaurant.models.Category;
import com.restaurant.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Long> {

    Boolean existsProductByName(String name);

    List<Product> findAllByCategoryId(Long id);
}
