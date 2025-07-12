package ecom_project.demo.Service;

import ecom_project.demo.Model.User;
import ecom_project.demo.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MultipartFile imageFile;

    @InjectMocks
    private UserService userService;

    @Mock
    private BCryptPasswordEncoder encoder;

    private User user;

    @BeforeEach
    void setUp() {
        // Initializare obiect user pentru test
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
    }

    @Test
    void addUser_shouldSaveUser() throws Exception {
        // Arrange
        when(imageFile.getOriginalFilename()).thenReturn("image.jpg");
        when(imageFile.getContentType()).thenReturn("image/jpeg");
        when(imageFile.getBytes()).thenReturn(new byte[]{1, 2, 3});
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(encoder.encode("password")).thenReturn("encodedPassword");

        // Act
        userService.Add(user, imageFile);

        // Assert
        verify(userRepository, times(1)).save(user);
        assert user.getPassword().equals("encodedPassword");
        assert user.getImageName().equals("image.jpg");
        assert user.getImageType().equals("image/jpeg");
    }
}
