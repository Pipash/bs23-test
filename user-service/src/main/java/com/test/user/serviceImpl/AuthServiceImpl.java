package com.test.user.serviceImpl;

import com.test.user.config.JwtUtils;
import com.test.user.exceptions.APIException;
import com.test.user.payload.SignupRequest;
import com.test.user.model.User;
import com.test.user.repository.UserRepository;
import com.test.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, User> redisTemplate;

    @Autowired
    JwtUtils jwtUtils;
    @Override
    public User createUser(SignupRequest signupRequest) {
        log.info("user creation started inside createUser method in AuthServiceImpl");
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        User newUser = userRepository.save(user);
        newUser.setPassword(null);
        return newUser;
    }

    @Override
    public User getUser(Long id) {
        log.info("starting getUser inside AuthServiceImpl to get single user slot:");
        String key = "user_"+id;
        final ValueOperations<String, User> ops = redisTemplate.opsForValue();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            log.info("data fetched from redis cache");
            return ops.get(key);
        }

        log.info("fetching data from database");
        final User user = userRepository.findById(id).orElseThrow(()-> new APIException("data not found"));

        user.setPassword(null);
        ops.set(key, user);
        log.info("data added to redis");

        return user;
    }

    @Override
    public User updateUser(Long id, SignupRequest request) {
        final String key = "user_"+id;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            redisTemplate.delete(key);
            log.info("redis data removed due to update");
        }
        log.info("starting updateUser inside AuthServiceImpl to update single user:");
        return userRepository.findById(id).map(user -> {
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            User newUser = userRepository.save(user);
            newUser.setPassword(null);
            return newUser;
        }).orElseThrow(() -> new APIException("data not found with given id"));
    }

    @Override
    public void deleteUser(Long id) {
        final String key = "user_"+id;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            redisTemplate.delete(key);
            log.info("redis data removed due to deletion");
        }

        log.info("starting deleteUser inside AuthServiceImpl to delete single user:");
        if (userRepository.findById(id).isEmpty()) {
            throw new APIException("data not found with given id");
        }
        userRepository.deleteById(id);
    }

    @Override
    public String generateToken(String username) {
        log.info("generating token inside generateToken of AuthServiceImpl");
        return jwtUtils.generateToken(username);
    }

    @Override
    public void validateToken(String token) {
        log.info("validating token inside validateToken of AuthServiceImpl");
        jwtUtils.validateToken(token);
    }
}
