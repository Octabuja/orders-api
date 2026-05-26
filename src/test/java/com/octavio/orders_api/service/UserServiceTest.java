package com.octavio.orders_api.service;

import com.octavio.orders_api.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import com.octavio.orders_api.model.User;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.mockito.Mockito.when;
import com.octavio.orders_api.dto.UserDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUserWhenEmailExists() {

        User user = new User();

        user.setName("Juan");
        user.setEmail("juan@mail.com");

        when(userRepository.findByEmail("juan@mail.com"))
                .thenReturn(Optional.of(user));

        UserDTO result =
                userService.getUserByEmail("juan@mail.com");

        assertEquals("Juan", result.getName());
        assertEquals("juan@mail.com", result.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenEmailDoesNotExist() {

        when(userRepository.findByEmail("emailinexistente"))
                .thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> userService.getUserByEmail("emailinexistente")
        );
    }
}
