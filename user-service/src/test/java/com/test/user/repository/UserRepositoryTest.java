package com.test.user.repository;

import com.test.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        //String userName = "someone";
        User user = new User("someone", "abcd@abcd.com", "12345678");
        userRepository.save(user);
    }

    @Test
    void findByUsername() {
        // given

        //when
        Optional<User> optionalUser = userRepository.findByUsername("someone");
        User expectedUser = null;
        if (optionalUser.isPresent()) {
            expectedUser = optionalUser.get();
        }

        //then
        assert expectedUser != null;
        assertThat(expectedUser.getUsername()).isEqualTo("someone");
    }

    @Test
    void existsByUsername() {
        //when
        Boolean expected = userRepository.existsByUsername("someone");

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void existsByEmail() {
        //when
        Boolean expected = userRepository.existsByEmail("abcd@abcd.com");

        //then
        assertThat(expected).isTrue();
    }
}