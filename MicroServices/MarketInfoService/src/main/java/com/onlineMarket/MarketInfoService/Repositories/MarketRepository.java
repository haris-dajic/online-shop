package com.onlineMarket.MarketInfoService.Repositories;

import com.onlineMarket.MarketInfoService.Models.Market;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends CrudRepository<Market, Integer> {
}
