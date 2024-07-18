package com.timskiproekt.pagepicks.controller;

import com.timskiproekt.pagepicks.model.User;
import com.timskiproekt.pagepicks.model.UserDto;
import com.timskiproekt.pagepicks.security.JwtUtil;
import com.timskiproekt.pagepicks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        User user = userService.registerNewUser(userDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null && user.getPassword().equals(userDto.getPassword())) {
            String token = jwtUtil.generateToken(userDto.getUsername());
            return ResponseEntity.ok("Bearer " + token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
