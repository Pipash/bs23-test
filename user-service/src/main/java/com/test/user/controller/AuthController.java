package com.test.user.controller;

import com.test.user.component.ServiceResponse;
import com.test.user.exceptions.APIException;
import com.test.user.model.User;
import com.test.user.payload.LoginRequest;
import com.test.user.payload.SignupRequest;
import com.test.user.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ServiceResponse> addUser(@Valid @RequestBody SignupRequest signupRequest) {
        log.info("creating user started in addUser of AuthController");
        User user = authService.createUser(signupRequest);
        log.info("user creation is complete in addUser of AuthController");
        return new ResponseEntity<>(new ServiceResponse("user is created", user), HttpStatus.CREATED);
    }

    @GetMapping("/user-details/{id}")
    public ResponseEntity<ServiceResponse> getUserDetails(@PathVariable Long id) {
        log.info("getting a single user started in getUserDetails of AuthController");
        User user = authService.getUser(id);
        log.info("getting a single user complete in getUserDetails of AuthController");
        return new ResponseEntity<>(new ServiceResponse("user information", user), HttpStatus.OK);
    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity<ServiceResponse> modifyUser(@PathVariable Long id, @Valid @RequestBody SignupRequest request) {
        log.info("updating a single user in modifyUser of AuthController");
        User user = authService.updateUser(id, request);
        log.info("user update complete in modifyUser of AuthController");
        return new ResponseEntity<>(new ServiceResponse("user information updated", user), HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<ServiceResponse> removeUser(@PathVariable Long id) {
        log.info("deleting a single user started in removeUser of AuthController");
        authService.deleteUser(id);
        log.info("user deletion completed in removeUser of AuthController");
        return new ResponseEntity<>(new ServiceResponse("user information deleted", null), HttpStatus.OK);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody LoginRequest loginRequest) {
        log.info("validating login and generating token by jwt");
         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
         if (authentication.isAuthenticated()) {
             return authService.generateToken(loginRequest.getUsername());
         } else {
             throw new APIException("invalid username or password!");
         }

    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        log.info("validating jwt token for authentication");
        authService.validateToken(token);
        return "token is validated";
    }
}
