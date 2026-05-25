package com.octavio.orders_api.service;

import com.octavio.orders_api.model.User;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    void shouldReturnUserWhenEmailExists() {

        User user = new User();

        user.setName("Juan");
        user.setEmail("juan@mail.com");

    }
}
