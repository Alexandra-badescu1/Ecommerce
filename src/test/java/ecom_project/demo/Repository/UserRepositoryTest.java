package ecom_project.demo.Repository;

import ecom_project.demo.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername_shouldReturnUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password"); // Add other required fields
        userRepository.save(user);

        Optional<User> found = Optional.ofNullable(userRepository.findByUsername("testuser"));

        assertTrue(found.isPresent());
        assertEquals("testuser", found.get().getUsername());
    }
}
