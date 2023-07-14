package library.controller;

import javax.validation.Valid;
import library.dto.request.UserRequestDto;
import library.dto.response.UserResponseDto;
import library.model.User;
import library.service.AuthenticationService;
import library.service.mapper.DtoMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final DtoMapper<User, UserRequestDto, UserResponseDto> dtoMapper;

    public AuthenticationController(AuthenticationService authenticationService,
                                    DtoMapper<User, UserRequestDto, UserResponseDto> dtoMapper) {
        this.authenticationService = authenticationService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/registration-form")
    public String getAddNewRegistrationPage() {
        return "forward:/static/registerUser.html";
    }

    @ResponseBody
    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRequestDto requestDto) {
        User user = authenticationService.register(requestDto.getEmail(), requestDto.getPassword());
        return dtoMapper.toDto(user);
    }
}
