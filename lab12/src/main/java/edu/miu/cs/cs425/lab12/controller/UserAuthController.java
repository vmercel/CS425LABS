package edu.miu.cs.cs425.lab12.controller;

import edu.miu.cs.cs425.lab12.dto.UserAuthRequest;
import edu.miu.cs.cs425.lab12.dto.UserRegistrationRequest;
import edu.miu.cs.cs425.lab12.security.JWTManagementUtilityService;
import edu.miu.cs.cs425.lab12.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTManagementUtilityService jwtService;
    private final UserService userService;

    public UserAuthController(AuthenticationManager authenticationManager,
                              JWTManagementUtilityService jwtService,
                              UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody UserAuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        // Set the authentication in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateToken(request.getEmail());
        System.out.println("Generated JWT for: " + request.getEmail() + " - " + jwt); // Debug log
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }
}