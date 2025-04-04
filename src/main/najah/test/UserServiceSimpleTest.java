package main.najah.test;

import main.najah.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceSimpleTest {

    UserService userService = new UserService();

    @ParameterizedTest
    @CsvSource({"admin,1234,true", "admin,wrong,false", "user,1234,false"})
    @DisplayName("Authentication tests with multiple values")
    void testAuthentication(String username, String password, boolean expected) {
        assertEquals(expected, userService.authenticate(username, password));
    }

    @ParameterizedTest
    @CsvSource({"test@example.com,true", "invalidEmail,false", "null,false"})
    @DisplayName("Email validation test")
    void testEmailValidation(String email, boolean expected) {
        boolean result = email == null ? userService.isValidEmail(null) : userService.isValidEmail(email);
        assertEquals(expected, result);
    }

    @Test
    @Timeout(1)
    @DisplayName("Authenticate timeout test")
    void testTimeout() {
        assertTrue(userService.authenticate("admin", "1234"));
    }
}
