package com.example.test.service;

import com.example.test.dto.AuthenticationDto;
import com.example.test.dto.AuthenticationResponse;
import com.example.test.dto.UserDto;
import com.example.test.entity.User;
import com.example.test.jwt.JwtProvider;
import com.example.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    public User addNewUser(UserDto userDto) {
        User user = User.builder().username(userDto.getUsername()).password(passwordEncoder(userDto.getPassword())).build();
        return userRepository.save(user);
    }

    public AuthenticationResponse authenticateUser(AuthenticationDto authenticationDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (authenticationDto.getUsername(), authenticationDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtProvider.generateToken(authentication);

        return new AuthenticationResponse(jwtToken);
    }

    private String passwordEncoder(String password) {
        return passwordEncoder.encode(password);
    }
}
