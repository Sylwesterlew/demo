package lew.sylwester.demo.domain.repository;


import lew.sylwester.demo.domain.model.DemoUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<DemoUser, Long> {

    Optional<DemoUser> findByLogin(String login);
}
