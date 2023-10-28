package com.firstdecision.appfirstdecisionbackend.service;

import com.firstdecision.appfirstdecisionbackend.model.dto.UserCredentialDTO;
import com.firstdecision.appfirstdecisionbackend.model.entity.User;
import com.firstdecision.appfirstdecisionbackend.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login() {
        when(userRepository.findByEmail("teste@teste.com")).thenReturn(getUser());

        boolean success = userService.login(getUserCredentialDTO());

        Assertions.assertTrue(success);
    }

    private User getUser() {
        return new User(1L, "name", "tete@teste.com", hashPassword());
    }

    private UserCredentialDTO getUserCredentialDTO() {
        return new UserCredentialDTO("teste@teste.com", "password");
    }

    private static String hashPassword() {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw("password", salt);
    }

}
