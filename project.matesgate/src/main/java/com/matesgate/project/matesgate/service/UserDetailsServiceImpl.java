package com.matesgate.project.matesgate.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.matesgate.project.matesgate.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo repository;

    public UserDetailsServiceImpl(UserRepo repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) repository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}