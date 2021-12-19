package com.ap.springsecuritydemo.service;

import com.ap.springsecuritydemo.model.MyUserDetail;
import com.ap.springsecuritydemo.model.User;
import com.ap.springsecuritydemo.repository.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepositoty userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(()->new UsernameNotFoundException("Username not found"));
        return user.map(MyUserDetail::new).get();
    }
}
