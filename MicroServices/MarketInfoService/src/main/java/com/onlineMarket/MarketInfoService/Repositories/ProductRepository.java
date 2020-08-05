package com.onlineMarket.MarketInfoService.Repositories;

import com.onlineMarket.MarketInfoService.Models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Iterable<Product> findProductsByName(String naziv);
    Iterable<Product> findProductsByCategory(String category);
}
