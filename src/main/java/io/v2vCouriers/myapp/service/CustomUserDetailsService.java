package io.v2vCouriers.myapp.service;

import io.v2vCouriers.myapp.model.User;
import io.v2vCouriers.myapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByemail(email).orElseThrow(() ->
                new UsernameNotFoundException("No user found " + email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),new ArrayList<>());
    }

}

