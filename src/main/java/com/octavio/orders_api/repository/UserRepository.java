package com.octavio.orders_api.repository;

import com.octavio.orders_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository encargado de interactuar con MySQL
// para operaciones relacionadas con usuarios
public interface UserRepository extends JpaRepository<User, Long> {

    // Verifica si ya existe un usuario con el email indicado
    boolean existsByEmail(String email);

}