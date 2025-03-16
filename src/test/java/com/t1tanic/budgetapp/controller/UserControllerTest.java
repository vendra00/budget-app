package com.t1tanic.budgetapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t1tanic.budgetapp.model.User;
import com.t1tanic.budgetapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class) // Load only UserController
@Import(UserControllerTest.TestConfig.class) // Provide mock manually
@AutoConfigureMockMvc(addFilters = false) // Disables security filters like CSRF
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService; // Now injected from TestConfig

    @BeforeEach
    void setup() {
        Mockito.reset(userService);
    }

    static class TestConfig {
        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class); // Manual mock
        }
    }

    @Test
    void registerUser_ShouldRejectInvalidEmail() throws Exception {
        User invalidUser = new User();
        invalidUser.setEmail("invalid-email"); // No @ symbol
        invalidUser.setPassword("password123");

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUser)))
                .andExpect(status().isBadRequest()) // Expect HTTP 400
                .andExpect(jsonPath("$.email").exists()) // Ensure the error message for email exists
                .andExpect(jsonPath("$.email").value("must be a well-formed email address")); // Verify error message
    }


    @Test
    void registerUser_ShouldAcceptValidEmail() throws Exception {
        User validUser = new User();
        validUser.setEmail("valid.email@example.com");
        validUser.setPassword("password123");

        Mockito.when(userService.registerUser(Mockito.any(User.class))).thenReturn(validUser);

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validUser)))
                .andExpect(status().isOk()); // Expect success
    }
}
