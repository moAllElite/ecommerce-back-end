package com.flyobs.ecommercebackend.services.impl;

import com.flyobs.ecommercebackend.entities.AppUser;
import com.flyobs.ecommercebackend.services.IAccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private  IAccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser= accountService.loadUserByUsername(username);
        if(appUser ==null) throw  new UsernameNotFoundException("No user was found with provided username");
        
        return null;
    }
}
