package com.flyobs.ecommercebackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@AllArgsConstructor @NoArgsConstructor @Data
@Builder
@Entity
public class AppRole {
    @Id
    private String role;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppUser> users;
}
