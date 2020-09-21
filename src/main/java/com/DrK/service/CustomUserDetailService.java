package com.DrK.service;

import com.DrK.Entities.UserEntity;
import com.DrK.repositories.UserRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetailService{
    
    private UserRepository userRepository;

    public String loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity user = userRepository.findByName(username);
        if (user==null) {
            throw new UsernameNotFoundException(username);
        }
        return user.getName();
    }
}