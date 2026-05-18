package com.octavio.orders_api.service;

import com.octavio.orders_api.dto.UserDTO;
import com.octavio.orders_api.model.User;
import com.octavio.orders_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {

        if (user.getName() == null || user.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }

        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email inválido");
        }

        boolean exists = userRepository.existsByEmail(user.getEmail());

        if (exists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email ya existe");
        }

        return userRepository.save(user);
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();

    }

    public User getUserById (Long id){

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado")
                );
    }


    public User updateUser (Long id, User updatedUser){

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado")
                );

            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());

            return userRepository.save(user);
    }

    public void deleteUser (Long id){

        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        userRepository.deleteById(id);
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getName(), user.getEmail());
    }

}
