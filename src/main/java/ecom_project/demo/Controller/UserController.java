package ecom_project.demo.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecom_project.demo.Model.User;
import ecom_project.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> login(@RequestBody User user) {
           return  new ResponseEntity<>(userService.verifyLogIn(user), HttpStatus.OK);
    }

}
