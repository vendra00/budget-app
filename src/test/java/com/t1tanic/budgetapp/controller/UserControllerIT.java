package com.t1tanic.budgetapp.controller;

import com.t1tanic.budgetapp.config.PostgresTestContainer;
import com.t1tanic.budgetapp.repository.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerIT {

    @Container
    private static final PostgresTestContainer postgres = PostgresTestContainer.getInstance();

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setupRestAssured() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @DynamicPropertySource
    static void configureDatabaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    @Order(1)
    void shouldRegisterUserSuccessfully() {
        String userJson = """
                {
                    "email": "testuser@example.com",
                    "password": "securepassword"
                }
                """;

        RestAssured.given()
                .contentType(JSON)
                .body(userJson)
                .when()
                .post("/users/register")
                .then()
                .statusCode(200)
                .body("email", equalTo("testuser@example.com"));
    }

    @Test
    @Order(2)
    void shouldNotAllowDuplicateUserRegistration() {
        String userJson = """
                {
                    "email": "testuser@example.com",
                    "password": "securepassword"
                }
                """;

        RestAssured.given()
                .contentType(JSON)
                .body(userJson)
                .when()
                .post("/users/register")
                .then()
                .statusCode(409) // Conflict due to UserAlreadyExistsException
                .body("error", equalTo("User Already Exists"));
    }

    @Test
    @Order(3)
    void shouldGetUserByEmail() {
        RestAssured.given()
                .when()
                .get("/users/testuser@example.com")
                .then()
                .statusCode(200)
                .body("email", equalTo("testuser@example.com"));
    }

    @Test
    @Order(4)
    void shouldReturnNotFoundForUnknownUser() {
        RestAssured.given()
                .when()
                .get("/users/unknown@example.com")
                .then()
                .statusCode(404)
                .body("error", equalTo("User Not Found"));
    }
}
