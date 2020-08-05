package com.onlineMarket.EurekaDiscoveryService.Repositories;

import com.onlineMarket.EurekaDiscoveryService.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    Boolean existsUserByEmail(String email);
    Boolean existsUserByPhoneNumber(String brojTelefona);
    User findUserByEmail(String email);
    Boolean existsUserByEmailAndPassword(String email, String password);

}
