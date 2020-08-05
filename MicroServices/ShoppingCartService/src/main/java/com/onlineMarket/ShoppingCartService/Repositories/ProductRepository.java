package com.onlineMarket.ShoppingCartService.Repositories;

import com.onlineMarket.ShoppingCartService.Models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
