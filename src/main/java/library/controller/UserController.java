package library.controller;

import java.util.List;
import java.util.stream.Collectors;
import library.dto.request.UserRequestDto;
import library.dto.response.UserResponseDto;
import library.model.User;
import library.service.UserService;
import library.service.mapper.DtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final DtoMapper<User, UserRequestDto, UserResponseDto> dtoMapper;

    public UserController(UserService userService,
                          DtoMapper<User, UserRequestDto, UserResponseDto> dtoMapper) {
        this.userService = userService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Long id) {
        return dtoMapper.toDto(userService.get(id));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id,
                                  @RequestBody UserRequestDto userRequestDto) {
        User role = userService.get(id);
        User user = dtoMapper.toModel(userRequestDto);
        user.setId(id);
        user.setRoles(role.getRoles());
        userService.update(user);
        return dtoMapper.toDto(user);
    }
}
