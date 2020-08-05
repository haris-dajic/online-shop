package com.onlineMarket.ShoppingCartService.UnitTests;

import com.onlineMarket.ShoppingCartService.Models.Product;
import com.onlineMarket.ShoppingCartService.Repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public class ShoppingCartServiceTest {

    @Autowired
    UserRepository userRepository;

}
