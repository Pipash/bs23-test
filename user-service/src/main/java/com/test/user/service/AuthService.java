package com.test.user.service;

import com.test.user.model.User;
import com.test.user.payload.SignupRequest;

public interface AuthService {
    User createUser(SignupRequest signupRequest);

    User getUser(Long id);

    User updateUser(Long id, SignupRequest request);

    void deleteUser(Long id);

    String generateToken(String username);

    void validateToken(String token);
}
