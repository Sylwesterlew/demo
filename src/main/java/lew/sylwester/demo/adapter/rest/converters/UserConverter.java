package lew.sylwester.demo.adapter.rest.converters;

import lew.sylwester.demo.adapter.rest.dto.UserDto;
import lew.sylwester.demo.domain.model.DemoUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserDto execute(DemoUser user) {
        return createUserDto(user);
    }

    public List<UserDto> execute(List<DemoUser> users) {
        return users
                .stream()
                .map(this::createUserDto)
                .collect(Collectors.toList());
    }

    public DemoUser execute(UserDto userDto) {
        return DemoUser.builder()
                .login(userDto.getLogin())
                .role(userDto.getRole())
                .build();
    }

    private UserDto createUserDto(DemoUser user) {
        return UserDto.builder()
                .login(user.getLogin())
                .role(user.getRole())
                .build();
    }
}
