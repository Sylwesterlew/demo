package lew.sylwester.demo.domain;

import com.google.common.collect.Lists;
import lew.sylwester.demo.domain.model.DemoUser;
import lew.sylwester.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public Optional<DemoUser> getUser(String login) {
        return userRepository.findByLogin(login);
    }

    public List<DemoUser> getAllUsers() {
        Iterable<DemoUser> response = userRepository.findAll();
        return Lists.newArrayList(response);
    }

    public Optional<Long> saveUser(DemoUser user) {
        try {
            return Optional.of(userRepository.save(user).getId());
        } catch (DataIntegrityViolationException e) {
            return Optional.empty();
        }
    }

}
