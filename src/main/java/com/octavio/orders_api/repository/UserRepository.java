package com.octavio.orders_api.repository;

import com.octavio.orders_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository encargado de interactuar con MySQL
// para operaciones relacionadas con usuarios
public interface UserRepository extends JpaRepository<User, Long> {

    // Verifica si ya existe un usuario con el email indicado
    boolean existsByEmail(String email);

    // Buscar usuario por Email , puede o no devolverlo en caso de no existir (optional)
    Optional<User> findByEmail(String email);
}