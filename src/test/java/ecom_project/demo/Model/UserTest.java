package ecom_project.demo.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void generateUsername() {
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("");
        user.generateUsername();
        assertEquals("johndoe", user.getUsername());
    }

    @Test
    void getId() {
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    void getFirstName() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
    }

    @Test
    void getLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    @Test
    void getEmail() {
        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void getPassword() {
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }

    @Test
    void getImageName() {
        user.setImageName("profile.jpg");
        assertEquals("profile.jpg", user.getImageName());
    }

    @Test
    void getImageType() {
        user.setImageType("image/jpeg");
        assertEquals("image/jpeg", user.getImageType());
    }

    @Test
    void getImageDate() {
        byte[] data = {1, 2, 3};
        user.setImageDate(data);
        assertArrayEquals(data, user.getImageDate());
    }

    @Test
    void getRole() {
        user.setRole(Role.USER);
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void getUsername() {
        user.setUsername("johndoe");
        assertEquals("johndoe", user.getUsername());
    }

    @Test
    void setId() {
        user.setId(5);
        assertEquals(5, user.getId());
    }

    @Test
    void setFirstName() {
        user.setFirstName("Alice");
        assertEquals("Alice", user.getFirstName());
    }

    @Test
    void setLastName() {
        user.setLastName("Johnson");
        assertEquals("Johnson", user.getLastName());
    }

    @Test
    void setEmail() {
        user.setEmail("alice@example.com");
        assertEquals("alice@example.com", user.getEmail());
    }

    @Test
    void setPassword() {
        user.setPassword("securepass");
        assertEquals("securepass", user.getPassword());
    }

    @Test
    void setImageName() {
        user.setImageName("avatar.png");
        assertEquals("avatar.png", user.getImageName());
    }

    @Test
    void setImageType() {
        user.setImageType("image/png");
        assertEquals("image/png", user.getImageType());
    }

    @Test
    void setImageDate() {
        byte[] data = {10, 20, 30};
        user.setImageDate(data);
        assertArrayEquals(data, user.getImageDate());
    }

    @Test
    void setRole() {
        user.setRole(Role.ADMIN);
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void setUsername() {
        user.setUsername("adminuser");
        assertEquals("adminuser", user.getUsername());
    }
}