package com.matesgate.project.matesgate.controlller;
import java.util.Optional;

//import org.hibernate.boot.jaxb.JaxbLogger_.logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matesgate.project.matesgate.model.LoginRequest;
import com.matesgate.project.matesgate.model.User;
import com.matesgate.project.matesgate.repository.UserRepo;
//import com.matesgate.project.matesgate.model.Token;

@RestController
@CrossOrigin
@RequestMapping("/")
public class LoginController {
	private  UserRepo userRepo;
	private PasswordEncoder passwordEncoder;
	
	 public LoginController(UserRepo userRepository,PasswordEncoder passwordEncoder) {
	        this.userRepo = userRepository;
	        this.passwordEncoder=passwordEncoder;
	    } 
	 
	 @PostMapping("api/login")
	 public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
	     Optional<User> userOptional = userRepo.findByUsername(loginRequest.getUsername());
	     
	     System.out.println(loginRequest.getUsername());
	     if (userOptional.isPresent()) {
	         User user = userOptional.get();
	         // Compare the raw password from the request with the encoded password from the database
	         if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
	             return ResponseEntity.ok("Login successful!");
	         } else {
	             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: Invalid username or password.");
	         }
	     }
	     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: User not found.");
	 }
}
