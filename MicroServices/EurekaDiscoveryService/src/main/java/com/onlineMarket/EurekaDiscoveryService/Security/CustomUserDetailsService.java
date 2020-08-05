package com.onlineMarket.EurekaDiscoveryService.Security;

import com.onlineMarket.EurekaDiscoveryService.Communication.RegistrationServicePublisher;
import com.onlineMarket.EurekaDiscoveryService.Models.User;
import com.onlineMarket.EurekaDiscoveryService.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegistrationServicePublisher registrationServicePublisher;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(username);

        if(user == null)
            throw new UsernameNotFoundException("User sa datim emailom nije pronađen");

        registrationServicePublisher.sendUserIDMessage(user.getId());
        registrationServicePublisher.sendUserNameAndSurnameMessage(user.getName() + " " + user.getSurname());
        registrationServicePublisher.sendUserRoleMessage(user.getRole());
        return new CustomUserDetails(user);
    }
}
