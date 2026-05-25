package com.octavio.orders_api.controller;

import com.octavio.orders_api.dto.CreateUserRequestDTO;
import com.octavio.orders_api.dto.UpdateUserRequestDTO;
import com.octavio.orders_api.dto.UserDTO;
import com.octavio.orders_api.model.User;
import com.octavio.orders_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// Controlador REST encargado de manejar las peticiones HTTP relacionadas con usuarios
@RestController

// Todas las rutas de esta clase comenzarán con /users
@RequestMapping("/users")
public class UserController {

    // Referencia al service donde vive la lógica de negocio
    private final UserService userService;

    // Spring inyecta automáticamente el UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint GET /users
    // Devuelve una lista de usuarios utilizando DTOs para controlar los datos expuestos
    @GetMapping
    public Page<UserDTO> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    // Endpoint POST /users
    // Convierte automáticamente el JSON recibido en un CreateUserRequestDTO
    @PostMapping
    public UserDTO createUser(@Valid @RequestBody CreateUserRequestDTO request) {

        // El controller delega la lógica de creación al service
        return userService.createUser(request);
    }

    // Endpoint GET /users/{id}
    // Busca un usuario por su ID
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Endpoint GET /users/email/{email}
    //Busca un usuario por su email
    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(
            @PathVariable String email) {

        return userService.getUserByEmail(email);
    }

    // Endpoint PUT /users/{id}
    // Actualiza un usuario existente
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id,@Valid @RequestBody UpdateUserRequestDTO request) {
        return userService.updateUser(id, request);
    }

    // Endpoint DELETE /users/{id}
    // Elimina un usuario por ID

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}