package com.onlineMarket.EurekaDiscoveryService.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.onlineMarket.EurekaDiscoveryService.Communication.RegistrationServicePublisher;
import com.onlineMarket.EurekaDiscoveryService.CustomExceptionHandlers.CustomException;
import com.onlineMarket.EurekaDiscoveryService.CustomExceptionHandlers.InvalidEmailException;
import com.onlineMarket.EurekaDiscoveryService.CustomExceptionHandlers.InvalidPhoneNumberException;
import com.onlineMarket.EurekaDiscoveryService.Models.User;
import com.onlineMarket.EurekaDiscoveryService.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegistrationServicePublisher registrationServicePublisher;

    @RequestMapping(method = RequestMethod.POST, value = "/register/user")
    public void registerUser(@RequestBody Map<String, String> data) throws Exception{
        /*User newUser = getRegistrationUserData(data);
        userRepository.save(newUser);
        registrationServicePublisher.sendUserCreatedMessage(newUser);*/
    }

    public User getRegistrationUserData(Map<String, String> data) throws Exception {
        String name = data.get("name");
        String surname = data.get("surname");
        String address = data.get("address");
        String phoneNumber = data.get("phoneNumber");
        String email = data.get("email");
        String password = data.get("password");

        if(userRepository.existsUserByPhoneNumber(phoneNumber)){
            throw new InvalidPhoneNumberException("Phone number is already in use!");
        }
        else if(userRepository.existsUserByEmail(email)){
            throw new InvalidEmailException("Email is already in use!");
        }

        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        return new User(name, surname, address, phoneNumber, email, hashPassword, "USER");
    }
}
