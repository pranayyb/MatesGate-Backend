package com.matesgate.project.matesgate.controlller;



import com.matesgate.project.matesgate.model.AuthenticationResponse;
import com.matesgate.project.matesgate.model.LoginRequest;
//import com.matesgate.project.matesgate.model.Token;
import com.matesgate.project.matesgate.model.User;
import com.matesgate.project.matesgate.repository.UserRepo;
import com.matesgate.project.matesgate.service.AuthenticationService;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class AuthenticationController {

    private final AuthenticationService authService;
    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
            ) {
        return ResponseEntity.ok(authService.register(request));
    }

    
    @GetMapping("getUser/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(authService.getUser(id));
    }

}