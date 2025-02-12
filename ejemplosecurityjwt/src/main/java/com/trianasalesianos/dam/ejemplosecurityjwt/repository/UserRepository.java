package com.trianasalesianos.dam.ejemplosecurityjwt.repository;

import com.trianasalesianos.dam.ejemplosecurityjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    //Siempre devbuelve 1, por eso el First, ademas de que debe ser unico
    Optional<User> findFirstByUsername(String username);
}
