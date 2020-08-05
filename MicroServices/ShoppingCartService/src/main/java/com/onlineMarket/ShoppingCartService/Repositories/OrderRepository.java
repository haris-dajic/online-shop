package com.onlineMarket.ShoppingCartService.Repositories;

import com.onlineMarket.ShoppingCartService.Models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
