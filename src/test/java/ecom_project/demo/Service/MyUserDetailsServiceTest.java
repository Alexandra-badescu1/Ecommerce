package ecom_project.demo.Service;

import ecom_project.demo.Model.User;
import ecom_project.demo.Model.UserPrincipales;
import ecom_project.demo.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MyUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;

    @Test
    void loadUserByUsername_shouldReturnUserPrincipalesForValidUser() {
        // Setup mock user
        String username = "testuser";
        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setPassword("password");

        // Configure Mockito to return the mock user when the repository is called
        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        // Call the method under test
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

        // Verify the returned object is an instance of UserPrincipales
        assertTrue(userDetails instanceof UserPrincipales);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    void loadUserByUsername_shouldThrowExceptionForInvalidUser() {
        // Setup the case where the user does not exist
        String username = "invaliduser";

        // Configure Mockito to return null when the repository is called
        when(userRepository.findByUsername(username)).thenReturn(null);

        // Call the method and verify that the exception is thrown
        assertThrows(UsernameNotFoundException.class, () -> {
            myUserDetailsService.loadUserByUsername(username);
        });
    }
}
