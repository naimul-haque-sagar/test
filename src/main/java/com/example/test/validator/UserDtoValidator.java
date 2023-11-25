package com.example.test.validator;

import com.example.test.dto.UserDto;
import com.example.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserDtoValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        if (Objects.nonNull(userDto.getUsername()) && userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            errors.rejectValue("username", "username", "Username is taken");
        }
    }
}
