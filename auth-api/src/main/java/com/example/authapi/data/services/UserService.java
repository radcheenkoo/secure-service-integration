
package com.example.authapi.data.services;

import com.example.authapi.data.entities.User;
import com.example.authapi.data.repositories.UserRepository;
import com.example.authapi.exceptions.UserAlreadyExistsException;
import com.example.authapi.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public UserDetailsService getUserDetailsService() {
        return this::getUserByEmail;
    }
}
