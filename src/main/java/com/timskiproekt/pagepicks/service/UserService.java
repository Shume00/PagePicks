package com.timskiproekt.pagepicks.service;

import com.timskiproekt.pagepicks.model.User;
import com.timskiproekt.pagepicks.model.UserDto;
import com.timskiproekt.pagepicks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User registerNewUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.getRoles().add("ROLE_USER");

        return userRepository.save(user);
    }
}
