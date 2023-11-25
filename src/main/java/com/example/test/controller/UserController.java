package com.example.test.controller;

import com.example.test.dto.AuthenticationResponse;
import com.example.test.dto.UserDto;
import com.example.test.entity.User;
import com.example.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        User user = userService.addNewUser(userDto);

        return ResponseEntity.ok(UserDto.from(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody UserDto userDto) {
        AuthenticationResponse authenticationResponse = userService.authenticateUser(userDto);

        return ResponseEntity.ok(authenticationResponse);
    }
}
