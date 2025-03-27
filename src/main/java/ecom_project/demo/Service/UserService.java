package ecom_project.demo.Service;

import ecom_project.demo.Model.User;
import ecom_project.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
        userRepository.save(user);
    }

    public String verifyLogIn(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(authentication.toString());
        else
            return "User not logged in";
    }
}
