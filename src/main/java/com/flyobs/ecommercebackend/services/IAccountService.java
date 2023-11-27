package com.flyobs.ecommercebackend.services;

import com.flyobs.ecommercebackend.entities.AppRole;
import com.flyobs.ecommercebackend.entities.AppUser;
import jakarta.persistence.EntityExistsException;

public interface IAccountService {
    AppUser addNewUser(String username,String password,String email,String confirmPassword) ;

    AppRole addNewRole(String role) throws EntityExistsException;

    void addRoleToUser(String username,String role);

    void removeRoleFormUser(String username,String role);
    AppUser loadUserByUsername(String username);
}
