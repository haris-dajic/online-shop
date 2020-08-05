package com.OnlineMarket.AdminService.Repositories;

import com.OnlineMarket.AdminService.Models.ProductAmount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductAmountRepository extends CrudRepository<ProductAmount, Integer> {

    ProductAmount findProductAmountByMarket_IdAndProduct_Id(Integer market_id, Integer product_id);
    List<ProductAmount> findProductAmountsByMarket_Id(Integer market_id);
    void deleteAllByProduct_Id(Integer product_id);
    void deleteAllByMarket_Id(Integer market_id);
}
