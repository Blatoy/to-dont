package ch.hearc.todont.repositories;

import ch.hearc.todont.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String userName);
}