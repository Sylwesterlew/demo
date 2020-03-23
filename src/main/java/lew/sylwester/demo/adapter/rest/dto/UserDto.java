package lew.sylwester.demo.adapter.rest.dto;

import lew.sylwester.demo.domain.model.enu.Role;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserDto {
    private String login;
    private Role role;
}
