package com.example.test.dto;

import com.example.test.entity.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Username could not be empty")
    private String username;

    @NotEmpty(message = "Password could not be empty")
    private String password;

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public static User to(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
