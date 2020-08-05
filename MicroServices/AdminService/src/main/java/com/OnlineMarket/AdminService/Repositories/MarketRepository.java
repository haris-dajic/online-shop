package com.OnlineMarket.AdminService.Repositories;

import com.OnlineMarket.AdminService.Models.Market;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarketRepository extends CrudRepository<Market, Integer> {
}
