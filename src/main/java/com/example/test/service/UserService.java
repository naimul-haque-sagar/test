package com.example.test.service;

import com.example.test.dto.UserDto;
import com.example.test.entity.User;
import com.example.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User addNewUser(UserDto userDto) {
        return userRepository.save(UserDto.to(userDto));
    }
}
