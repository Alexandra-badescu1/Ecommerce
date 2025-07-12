package ecom_project.demo.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserPrincipalesTest {

    private User user;
    private UserPrincipales userPrincipales;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("user@example.com");
        user.setPassword("password123");
        userPrincipales = new UserPrincipales(user);
    }

    @Test
    void getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userPrincipales.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("USER", authorities.iterator().next().getAuthority());
    }

    @Test
    void getPassword() {
        assertEquals("password123", userPrincipales.getPassword());
    }

    @Test
    void getUsername() {
        assertEquals("user@example.com", userPrincipales.getUsername());
    }

    @Test
    void isAccountNonExpired() {
        assertTrue(userPrincipales.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertTrue(userPrincipales.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertTrue(userPrincipales.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertTrue(userPrincipales.isEnabled());
    }
}
