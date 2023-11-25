package com.example.test.controller;

import com.example.test.dto.AuthenticationDto;
import com.example.test.dto.AuthenticationResponse;
import com.example.test.dto.UserDto;
import com.example.test.entity.User;
import com.example.test.service.UserService;
import com.example.test.validator.UserDtoValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Objects;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserDtoValidator userDtoValidator;

    public static final String USER_DTO_BINDER = "userDto";

    @InitBinder(USER_DTO_BINDER)
    protected void initExperienceDtoBinder(WebDataBinder binder) {
        binder.addValidators(userDtoValidator);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        User user = userService.addNewUser(userDto);

        return ResponseEntity.ok(UserDto.from(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthenticationDto authenticationDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        AuthenticationResponse authenticationResponse = userService.authenticateUser(authenticationDto);

        if (Objects.nonNull(authenticationResponse.getJwtToken())) {
            return ResponseEntity.ok(authenticationResponse);
        }

        return ResponseEntity.badRequest().body("Wrong credentials");
    }
}
