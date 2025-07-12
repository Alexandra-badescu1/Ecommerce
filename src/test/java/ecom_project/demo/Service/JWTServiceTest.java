package ecom_project.demo.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

class JWTServiceTest {

    private JWTService jwtService;

    @BeforeEach
    void setUp() throws Exception {
        jwtService = new JWTService();
    }

    @Test
    void generateToken_shouldReturnValidToken() {
        String token = jwtService.generateToken("testuser");
        assertNotNull(token);
    }

    @Test
    void extractUsername_shouldReturnCorrectUsername() {
        String username = "testuser";
        String token = jwtService.generateToken(username);

        String extracted = jwtService.extractUsername(token);
        assertEquals(username, extracted);
    }

    @Test
    void validateToken_shouldReturnTrueForValidToken() {
        String username = "testuser";
        UserDetails userDetails = User.builder()
                .username(username)
                .password("dummy") // not used in validation
                .roles("USER")
                .build();

        String token = jwtService.generateToken(username);
        boolean isValid = jwtService.validateToken(token, userDetails);
        assertTrue(isValid);
    }

    @Test
    void validateToken_shouldReturnFalseForWrongUsername() {
        String token = jwtService.generateToken("actualUser");

        UserDetails wrongUser = User.builder()
                .username("anotherUser")
                .password("dummy")
                .roles("USER")
                .build();

        boolean isValid = jwtService.validateToken(token, wrongUser);
        assertFalse(isValid);
    }
}
