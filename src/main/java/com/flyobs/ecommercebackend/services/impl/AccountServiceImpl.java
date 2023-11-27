package com.flyobs.ecommercebackend.services.impl;

import com.flyobs.ecommercebackend.entities.AppRole;
import com.flyobs.ecommercebackend.entities.AppUser;
import com.flyobs.ecommercebackend.repositories.AppRoleRepository;
import com.flyobs.ecommercebackend.repositories.AppUserRepository;
import com.flyobs.ecommercebackend.services.IAccountService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@AllArgsConstructor
@Transactional
@Service
public class AccountServiceImpl implements IAccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository   appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) throws RuntimeException {
        AppUser appUser = appUserRepository.findByUsername(username);

        if(appUser!=null) throw  new EntityExistsException("This user already exist ");

        if(!password.equals(confirmPassword)) throw new EntityExistsException("Password not match");

        appUser= AppUser.builder()
                .userId(randomUUID().toString())
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        return  appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(String role) throws EntityExistsException {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole !=   null) {
            throw  new EntityExistsException("this role already exist");
        }
        appRole = AppRole.builder()
                .role(role)
                .build();
        return  appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).orElseThrow(
                () -> new EntityNotFoundException("No role was found with the provided id:"+role)
        );
        /*
         * quand le service porte l'annotation transactionnal l'orm fait un commit
         * à la fin de la transaction update automatique
         */
        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFormUser(String username, String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if ( appRole==null) throw  new EntityNotFoundException("No role was found with the provided id:"+role);
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null) throw new  EntityNotFoundException("No user was found with the provided username:"+username);
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
