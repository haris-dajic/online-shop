package com.onlineMarket.EurekaDiscoveryService.Controllers;

import com.onlineMarket.EurekaDiscoveryService.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class WebController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String home(){
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String registration(){
        return "registration";
    }
/*
    @RequestMapping(method = RequestMethod.GET, value = "/default")
    public String redirectAfterSuccessfulLogin(){

        Collection<? extends GrantedAuthority> authorities;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        authorities = auth.getAuthorities();
        String myRole = authorities.toArray()[0].toString();

        if(myRole.equals("USER"))
            return "redirect:/market-info-service/";
        return "redirect:/admin-service/";
    }*/

}
