package lew.sylwester.demo.adapter.rest;

import lew.sylwester.demo.adapter.rest.converters.UserConverter;
import lew.sylwester.demo.adapter.rest.dto.UserDto;
import lew.sylwester.demo.domain.UserUseCase;
import lew.sylwester.demo.domain.model.DemoUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController("demo")
@RequiredArgsConstructor
public class DemoRestApi {

    private final UserUseCase userUseCase;
    private final UserConverter userConverter;

    @GetMapping("user/{login}")
    public ResponseEntity<UserDto> getUser(@PathVariable String login) {
        Optional<DemoUser> user = userUseCase.getUser(login);
        if (user.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(userConverter.execute(user.get()));
    }

    @GetMapping("user")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<DemoUser> users = userUseCase.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(userConverter.execute(users));
    }

    @PostMapping("user")
    public ResponseEntity saveUser(@RequestBody UserDto user) {
        Optional<Long> response = userUseCase.saveUser(userConverter.execute(user));
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Login must be unique");
    }
}
