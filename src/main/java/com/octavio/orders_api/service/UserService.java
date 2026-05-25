package com.octavio.orders_api.service;

import com.octavio.orders_api.dto.CreateUserRequestDTO;
import com.octavio.orders_api.dto.UpdateUserRequestDTO;
import com.octavio.orders_api.dto.UserDTO;
import com.octavio.orders_api.model.User;
import com.octavio.orders_api.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// Contiene la lógica de negocio relacionada con usuarios
@Service
public class UserService {

    // Acceso al repository para interactuar con MySQL
    private final UserRepository userRepository;

    // Spring inyecta automáticamente el repository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Convierte una entidad User en un DTO para controlar
    // qué datos serán enviados al cliente
    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
                );
    }

    // Obtiene todos los usuarios de la base de datos
    // y los transforma a DTOs antes de devolverlos
    public Page<UserDTO> getUsers(Pageable pageable) {

        Page<User> users =
                userRepository.findAll(pageable);
        return users.map(this::toDTO);

    }

    // Crea un nuevo usuario a partir de los datos recibidos desde HTTP
    public UserDTO createUser(CreateUserRequestDTO request) {

        // El backend crea manualmente la entidad User
        // a partir del DTO recibido
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Verifica si el email ya existe en la base de datos
        boolean exists = userRepository.existsByEmail(user.getEmail());

        if (exists) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El email ya existe"
            );
        }

        // Guarda el usuario en MySQL
        User savedUser = userRepository.save(user);

        return toDTO(savedUser);
    }

    // Busca un usuario por ID
    public UserDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Usuario no encontrado"
                        )
                );
        return toDTO(user);
    }

    // Busca un usuario por Email
    public UserDTO getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Usuario no encontrado"
                        )
                );
        return toDTO(user);
    }

    // Actualiza los datos de un usuario existente
    public UserDTO updateUser(Long id, UpdateUserRequestDTO request) {

        // Busca el usuario actual en la base de datos
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Usuario no encontrado"
                        )
                );

        // Actualiza los campos modificables
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Guarda los cambios en MySQL
        userRepository.save(user);
        return toDTO(user);
    }

    // Elimina un usuario por ID
    public void deleteUser(Long id) {

        // Verifica si el usuario existe antes de eliminarlo
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Usuario no encontrado"
            );
        }

        // Elimina el usuario de la base de datos
        userRepository.deleteById(id);
    }
}