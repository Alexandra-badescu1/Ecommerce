package ecom_project.demo.Service;

import ecom_project.demo.Model.User;
import ecom_project.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void Add(User user, MultipartFile imageFile) throws Exception {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setImageDate(imageFile.getBytes());
        user.setImageName(imageFile.getOriginalFilename());
        user.setImageType(imageFile.getContentType());
        user.setRole(user.getRole());
        userRepository.save(user);
    }

    public String verifyLogIn(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(authentication.getName()); // use getName(), not toString()
            } else {
                throw new BadCredentialsException("Authentication failed");
            }
        } catch (AuthenticationException ex) {
            System.out.println("❌ Login error: " + ex.getMessage());
            throw ex; // or return a custom message
        }
    }


    public String getUserRole(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return String.valueOf(user.getRole());
        } else {
            return "User not found";
        }
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }
}
