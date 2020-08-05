package com.onlineMarket.ShoppingCartService.Repositories;

import com.onlineMarket.ShoppingCartService.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
