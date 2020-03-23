package lew.sylwester.demo.configuration;

import lew.sylwester.demo.domain.model.DemoUser;
import lew.sylwester.demo.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<DemoUser> optionalUser = userRepo.findByLogin(login);
        if (optionalUser.isPresent()) {
            DemoUser user = optionalUser.get();
            return User.withDefaultPasswordEncoder() //@Deprecated na szybkości na potrzeby demo :)
                    .username(user.getLogin())
                    .password(user.getLogin())
                    .roles(user.getRole().toString())
                    .build();
        } else {
            throw new RuntimeException("User not found : " + login);
        }
    }
}