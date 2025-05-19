package com.joaopem.embrace_family.model;

import com.joaopem.embrace_family.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "user_accounts")
@Data
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 300)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private UserRole userRole;

    @OneToOne(mappedBy = "userAccount", cascade = CascadeType.ALL)
    private AdoptiveParent adoptiveParent;

}
