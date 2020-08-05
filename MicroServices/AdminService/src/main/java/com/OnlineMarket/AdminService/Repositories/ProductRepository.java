package com.OnlineMarket.AdminService.Repositories;

import com.OnlineMarket.AdminService.Models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
