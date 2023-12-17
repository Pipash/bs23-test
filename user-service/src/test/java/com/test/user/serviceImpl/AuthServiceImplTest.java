package com.test.user.serviceImpl;

import com.test.user.model.User;
import com.test.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    private User user;

    @BeforeEach
    public void setup(){
        user = User.builder()
                .id(1L)
                .username("someone")
                .email("abc@abc.com")
                .password("12345678")
                .build();
    }
}
