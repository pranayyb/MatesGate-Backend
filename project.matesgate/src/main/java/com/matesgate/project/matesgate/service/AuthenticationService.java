//package com.matesgate.project.matesgate.service;
//
//import com.matesgate.project.matesgate.model.AuthenticationResponse;
//import com.matesgate.project.matesgate.model.Role;
//import com.matesgate.project.matesgate.model.Token;
//import com.matesgate.project.matesgate.model.User;
//import com.matesgate.project.matesgate.repository.TokenRepository;
//import com.matesgate.project.matesgate.repository.UserRepo;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AuthenticationService {
//
//    private final UserRepo repository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final TokenRepository tokenRepository;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationService(UserRepo repository,
//                                 PasswordEncoder passwordEncoder,
//                                 JwtService jwtService,
//                                 TokenRepository tokenRepository,
//                                 AuthenticationManager authenticationManager) {
//        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtService = jwtService;
//        this.tokenRepository = tokenRepository;
//        this.authenticationManager = authenticationManager;
//    }
//
//    public AuthenticationResponse register(User request) {
//
//        // check if user already exist. if exist than authenticate the user
//        if(repository.findByUsername(request.getUsername()).isPresent()) {
//            return new AuthenticationResponse(null, "User already exists", null);
//        }
//
//        User user = new User();
//        user.setEmail(request.getEmail());
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        
//        user.setRole(request.getRole());
//        user.setPhoneno(request.getPhoneno());
//
//        user = repository.save(user);
//
//        String jwt = jwtService.generateToken(user);
//
//        saveUserToken(jwt, user);
//
//        return new AuthenticationResponse(jwt, "User registration was successful", user);
//
//    }
//
////    public AuthenticationResponse authenticate(User request) {
////        authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(
////                        request.getUsername(),
////                        request.getPassword()
////                )
////        );
////
////        User user = repository.findByUsername(request.getUsername()).orElseThrow();
////        String jwt = jwtService.generateToken(user);
////
////        revokeAllTokenByUser(user);
////        saveUserToken(jwt, user);
////
////        return new AuthenticationResponse(jwt, "User login was successful", user);
////    }
//    
//
//    public AuthenticationResponse authenticate(User request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(),
//                        request.getPassword()
//                )
//        );
//
//        User user = repository.findByUsername(request.getUsername()).orElseThrow();
//        String jwt = jwtService.generateToken(user);
//
//        revokeAllTokenByUser(user);
//        saveUserToken(jwt, user);
//
//        User newUser = new User();
//        newUser.setUsername(user.getUsername());
//        newUser.setEmail(user.getEmail());
//        newUser.setRole(user.getRole());
//        newUser.setId(user.getId());
//        newUser.setPhoneno(user.getPhoneno());
//
//
//        return new AuthenticationResponse(jwt, "User login was successful", newUser);
//
//    }
//    
//    public User getUser(Integer id) {
//        return repository.findById(id).orElse(null);
//    }
//
//    private void revokeAllTokenByUser(User user) {
//        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
//        if(validTokens.isEmpty()) {
//            return;
//        }
//
//        validTokens.forEach(t-> {
//            t.setLoggedOut(true);
//        });
//
//        tokenRepository.saveAll(validTokens);
//    }
//
//    private void saveUserToken(String jwt, User user) {
//        Token token = new Token();
//        token.setToken(jwt);
//        token.setLoggedOut(false);
//        token.setUser(user);
//        tokenRepository.save(token);
//    }
//}
//







package com.matesgate.project.matesgate.service;

import com.matesgate.project.matesgate.model.AuthenticationResponse;
import com.matesgate.project.matesgate.model.Token;
import com.matesgate.project.matesgate.model.User;
import com.matesgate.project.matesgate.repository.TokenRepository;
import com.matesgate.project.matesgate.repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepo repository,
                                   PasswordEncoder passwordEncoder,
                                   JwtService jwtService,
                                   TokenRepository tokenRepository,
                                   AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request) {

        // check if user already exists
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "User already exists", null);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setPhoneno(request.getPhoneno());

        user = repository.save(user);

        String jwt = jwtService.generateToken(user);

        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, "User registration was successful", user);
    }

    public AuthenticationResponse authenticate(User request) {
    	
    	if (repository.findByUsername(request.getUsername()).isPresent()) {
    		Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            ); 

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = repository.findByUsername(request.getUsername()).orElseThrow();
            String jwt = jwtService.generateToken(user);

            revokeAllTokenByUser(user);
            saveUserToken(jwt, user);

            return new AuthenticationResponse(jwt, "User login was successful", user);
        }
    	else {
    		return new AuthenticationResponse(null, "User does not exist!!",null);
    	}
    	
        
    }

    public User getUser(Integer id) {
        return repository.findById(id).orElse(null);
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t -> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }
}

