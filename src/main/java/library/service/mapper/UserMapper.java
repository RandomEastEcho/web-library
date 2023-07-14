package library.service.mapper;

import library.dto.request.UserRequestDto;
import library.dto.response.UserResponseDto;
import library.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements DtoMapper<User, UserRequestDto, UserResponseDto> {
    @Override
    public UserResponseDto toDto(User model) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(model.getId());
        userResponseDto.setEmail(model.getEmail());
        return userResponseDto;
    }

    @Override
    public User toModel(UserRequestDto requestDto) {
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        return user;
    }
}
