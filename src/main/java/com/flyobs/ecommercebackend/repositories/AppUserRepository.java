package com.flyobs.ecommercebackend.repositories;

import com.flyobs.ecommercebackend.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppUserRepository extends JpaRepository<AppUser,String> {

    @Query(value = "SELECT u FROM AppUser u WHERE u.username=:username")
    AppUser findByUsername(String username);
}
