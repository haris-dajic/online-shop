package com.onlineMarket.ShoppingCartService.Repositories;

import com.onlineMarket.ShoppingCartService.Models.Market;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends CrudRepository<Market, Integer> {
}
