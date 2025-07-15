package ecom_project.demo.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecom_project.demo.Model.LoginResponse;
import ecom_project.demo.Model.User;
import ecom_project.demo.Model.UserPrincipales;
import ecom_project.demo.Model.UserProfileDTO;
import ecom_project.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
   @Autowired
   private UserService userService;


   @PostMapping(path = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ResponseEntity<String> addUser(
            @RequestPart("product") String userJson,
            @RequestPart("imageFile") MultipartFile imageFile) throws Exception {
       ObjectMapper objectMapper = new ObjectMapper();
       User user= objectMapper.readValue(userJson, User.class);

       userService.Add(user, imageFile);
       return ResponseEntity.ok("User added successfully");
   }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User user) {
        String token = userService.verifyLogIn(user);
        String role = userService.getUserRole(user.getEmail());
        // Or however you fetch role
        return new ResponseEntity<>(new LoginResponse(token, role), HttpStatus.OK);
    }
    @GetMapping(value = "/profile", produces = "application/json")
    public ResponseEntity<UserProfileDTO> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(new UserProfileDTO(user));
    }


}
